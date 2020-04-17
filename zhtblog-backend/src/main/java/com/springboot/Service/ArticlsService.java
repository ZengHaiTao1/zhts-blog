package com.springboot.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.RedisTemlateUtil.RedisService;
import com.springboot.mapper.ArticlsDao;
import com.springboot.mapper.CategoriesDao;
import com.springboot.mapper.DraftsarticlesDao;
import com.springboot.mapper.LabelDao;
import com.springboot.pojo.Articls;
import com.springboot.pojo.Categories;
import com.springboot.pojo.Draftsarticles;
import com.springboot.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ArticlsService {

    @Autowired
    private ArticlsDao articlsDao;

    @Autowired
    private DraftsarticlesDao draftsarticlesDao;

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private RedisService redisService;

    /**
     * 保存文章！
     * @param articls
     * @return
     */
    public  boolean saveActicle(Articls articls,String draftsArticleId){
        if(!Objects.isNull(draftsArticleId)){
            draftsarticlesDao.deleteById(draftsArticleId);
        }
        articls.setBrowse(0);
        articls.setTime(new Date());
        articls.setRecommended("0");
        articls.setLikearticls(0);
        int insert = articlsDao.insert(articls);
        if(insert<=0){
            return false;
        }
        String tags = articls.getTags();
        String[] taglist = tags.split(",");
        for (int i = 0 ; i < taglist.length ; i++ ){
            QueryWrapper qw = new QueryWrapper();
            qw.eq("name",taglist[i]);
            Label label = labelDao.selectOne(qw);
            if(Objects.isNull(label)){
            Label label1 = new Label();
                label1.setName(taglist[i]);
                label1.setDisplay("0");
                label1.setNum(1);
                labelDao.insert(label1);
              }else {
                label.setNum(label.getNum()+1);
                labelDao.updateById(label);
            }
        }
        String category = articls.getCategory();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("categories",category);
        Categories categories = categoriesDao.selectOne(qw);
        if(Objects.isNull(categories)){
            Categories categories1 = new Categories();
            categories1.setCategories(category);
            categories1.setDisplay("0");
            categories1.setNum(1);
            categoriesDao.insert(categories1);
        }else{
            categories.setNum(categories.getNum()+1);
            categoriesDao.updateById(categories);
        }
        //删除时间轴缓存
        redisService.removePattern("categorieTimeAxis/*");
        redisService.remove("acticlsTimeAxis");
        redisService.removePattern("tagTimeAxis/*");
        //删除分页缓存
        redisService.remove("getArticlsByPage");
        redisService.remove("getArticlsByDate");
        return  true;
    }

    /**
     * 保存文章到草稿箱
     * @param draftsarticlesr
     * @return
     */
    public Integer savedrAftsArticles( Draftsarticles draftsarticlesr){
        Integer id = draftsarticlesr.getId();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("id",id);
        Draftsarticles ds = draftsarticlesDao.selectOne(qw);
        if(!Objects.isNull(ds)){
            UpdateWrapper up = new UpdateWrapper();
            up.eq("id",id);
            int update = draftsarticlesDao.update(draftsarticlesr, up);
            if(update<=0){
                return  null;
            }
            return  id;
        }
        draftsarticlesr.setCreattime(new Date());
        int insert = draftsarticlesDao.insert(draftsarticlesr);
        if(insert<=0){
          return  null;
        }
        return  draftsarticlesr.getId();
    }

    /**
     * 获取文章根据分页数
     * @param i  当前页数
     * @return
     */
    public List<Articls> getArticlsByPage(Integer i){
        List<Articls> redis =(List<Articls>) redisService.hget("getArticlsByPage",i.toString());
        if(!Objects.isNull(redis)){
            return  redis;
        }
        QueryWrapper qw = new QueryWrapper();
        qw.orderByDesc("id");
//        qw.notIn("id",22);
        qw.select("id","html","markdown","title","browse","category","tags","likearticls","Recommended","time");
        IPage<Articls> page = new Page<>(i,10);
        IPage<Articls> articlsIPage = articlsDao.selectPage(page, qw);
        List<Articls> records = articlsIPage.getRecords();
        redisService.hset("getArticlsByPage",i.toString(),records,7);
        return  records;
    }

    /**
     * 通过时间获取文章数，用于后台仪表盘显示
     * @return
     */
    public  List<Map<Integer,Integer>> getArticlsByDate(){
        List<Map<Integer,Integer>> getArticlsByDate = (List<Map<Integer,Integer>>)redisService.get("getArticlsByDate");
        if(!Objects.isNull(getArticlsByDate)){
            System.out.println("redis");
            return  getArticlsByDate;
        }
        System.out.println("数据库");
        List<Map<Integer,Integer>> listDate = new ArrayList<>();
        Calendar a=Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        for(int i = 0 ; i < 12 ; i++){
            QueryWrapper qw = new QueryWrapper();
            qw.apply(" year(time)= '"+year+"'" );
            qw.apply("month(time)='"+(i+1)+"'");
//            qw.apply("date_format(time,'%Y-%m') = '"+year+"-"+(i+1)+"'" );
            Integer integer = articlsDao.selectCount(qw);
            if(Objects.isNull(integer)){
                Map<Integer,Integer> map = new HashMap<Integer,Integer>();
                map.put(i+1,0);
                listDate.add(map);
                continue;
            }
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            map.put(i+1,integer);
            listDate.add(map);
        }
        redisService.set("getArticlsByDate",listDate, 7);
        return  listDate;
    }

    /**
     * 根据推荐阅读返回文章列表
     * @param RecommendedReading
     * @return
     */
    public  List getArticlsByReading(String RecommendedReading){
        QueryWrapper qw = new QueryWrapper();
        qw.select("id","title","time","browse","likearticls");
         if(!Objects.isNull(RecommendedReading)){
             qw.eq("Recommended",RecommendedReading);
         }
        List getArticlsByReading = (List) redisService.get("getArticlsByReading");
        if(!Objects.isNull(getArticlsByReading)){
            return  getArticlsByReading;
        }
        List list = articlsDao.selectList(qw);
         redisService.set("getArticlsByReading",list,7);
        return  list;
    }


    /**
     * 获取时间轴数据
     * @return
     */
    public List getTimeAxis(String categorie ,String tag){
        if(Objects.isNull(categorie)&&Objects.isNull(tag)){
            List acticlsTimeAxis = (List)redisService.get("acticlsTimeAxis");
            if(!Objects.isNull(acticlsTimeAxis)){
                return  acticlsTimeAxis;
            }
        }else if(!Objects.isNull(categorie)){
            List categorieTimeAxis = (List)redisService.get("categorieTimeAxis/"+categorie);
            if(!Objects.isNull(categorieTimeAxis)){
                return  categorieTimeAxis;
            }
        }else if(!Objects.isNull(tag)) {
            List tagTimeAxis = (List)redisService.get("tagTimeAxis/"+tag);
            if(!Objects.isNull(tagTimeAxis)){
                return  tagTimeAxis;
            }
        }
        List data = new ArrayList();
        Calendar a=Calendar.getInstance();
        int i = a.get(Calendar.YEAR);
        while (i>=2020){
            Map dataList = new HashMap();
            dataList.put("year",i);
            QueryWrapper qw = new QueryWrapper();
            String categoriesName = null;
            if(!Objects.isNull(categorie)){
                Categories categories = categoriesDao.selectById(categorie);
                if(Objects.isNull(categories)){
                    return  null;
                }
                categoriesName = categories.getCategories();
                qw.eq("category",categories.getCategories());
            }
            String tagName = null;
            if(!Objects.isNull(tag)){
                Label label = labelDao.selectById(tag);
                if(Objects.isNull(label)){
                    return  null;
                }
                tagName = label.getName();
                qw.like("tags",label.getName());
            }
            qw.apply(" year(time)= '"+i+"'" );
            Integer yearnum = articlsDao.selectCount(qw);
            dataList.put("num",yearnum);
            List monthData = new ArrayList();
            for(int j = 11 ; j > -1 ; j--){
                QueryWrapper qwMonth = new QueryWrapper();
                if(!Objects.isNull(categorie)){
                    qwMonth.eq("category",categoriesName);
                }
                if(!Objects.isNull(tag)){
                    qwMonth.like("tags",tagName);
                }
                Map mapList = new HashMap();
                mapList.put("monthTitle",j+1);
                qwMonth.apply(" year(time)= '"+i+"'" );
                qwMonth.apply("month(time)='"+(j+1)+"'");
                Integer monthNum = articlsDao.selectCount(qwMonth);
                if(monthNum==0){
                    continue;
                }
                mapList.put("num",monthNum);
                qwMonth.select("id","title","time");
                qwMonth.orderByDesc("id");
                List list = articlsDao.selectList(qwMonth);
                mapList.put("articlelist",list);
                monthData.add(mapList);
            }
            dataList.put("month",monthData);
            data.add(dataList);
            i--;
        }
        if(Objects.isNull(categorie)&&Objects.isNull(tag)){
                redisService.set("acticlsTimeAxis",data,7);
        }else if(!Objects.isNull(categorie)){
            redisService.set("categorieTimeAxis/"+categorie,data,7);
        }else if(!Objects.isNull(tag)) {
            redisService.set("tagTimeAxis/"+tag,data,7);
        }
        return  data;
    }

        /**
         * 根据id获取文章
         * @param id
         * @return
         */
         public  Articls getArticlsById(Integer id){
             Articls getArticlsById = (Articls)redisService.hget("getArticlsById", id.toString());
             if(!Objects.isNull(getArticlsById)){
                 return  getArticlsById;
             }
             Articls articls = articlsDao.selectById(id);
             if(Objects.isNull(articls)){
                 return null;
             }
             redisService.hset("getArticlsById",id.toString(),articls,7);
             return  articls;
         }

    /**
     * 根据id增加阅读量
     * @param id
     * @return
     */
    public Boolean addBrowse(Integer id){
            QueryWrapper qw = new QueryWrapper();
            qw.select("browse","id");
            qw.eq("id",id);
             Articls articls = articlsDao.selectOne(qw);
             if(Objects.isNull(articls)){
                 return false;
             }
             UpdateWrapper qwset = new UpdateWrapper();
             qwset.eq("id",id);
             qwset.set("browse",articls.getBrowse()+1);
             int update = articlsDao.update(articls, qwset);
             if(update<=0){
                 return  false;
             }
             return  true;
         }

    /**
     * 根据id增加喜欢数
     * @param id
     * @return
     */
    public Boolean addLikearticls(Integer id){
             QueryWrapper qw = new QueryWrapper();
             qw.select("likearticls","id");
             qw.eq("id",id);
             Articls articls = articlsDao.selectOne(qw);
             UpdateWrapper qwset = new UpdateWrapper();
             qwset.eq("id",id);
             qwset.set("likearticls",articls.getLikearticls()+1);
             int update = articlsDao.update(articls, qwset);
             if(update<=0){
                 return  false;
             }
             return  true;
         }

    /**
     * 获取文章数量
     * @return
     */
    public  Integer getArticlNum(){
             Integer integer = articlsDao.selectCount(null);
             return  integer;
         }

    /**
     * 设置文章是否为推荐阅读
     * @param id
     * @param tf
     * @return
     */
         public boolean setArticlDisplay(String id,String tf){
        UpdateWrapper uw = new UpdateWrapper();
             uw.eq("id",id);
             uw.set("recommended",tf);
             int update = articlsDao.update(new Articls(), uw);
             if(update<=0){
                 return  false;
             }
             //删除推荐阅读缓存
             redisService.remove("getArticlsByReading");
             return  true;
         }

    /**
     * 修改文章
     * @param articls
     * @return
     */
    public boolean updateArticle(Articls articls){

            //删除文章原来的标签数量
        Articls oldArticls  = articlsDao.selectById(articls.getId());
        String tags1 = oldArticls.getTags();
        String[] oldTaglist = tags1.split(",");
        String tags = articls.getTags();
        String[] newTaglist = tags.split(",");
        outer:for (int i = 0; i <oldTaglist.length ; i++) {
            for (int j = 0; j <newTaglist.length ; j++) {
                if(oldTaglist[i]==newTaglist[j]){
                    //老标签的内容在新标签中找得到，说明还在，不删除
                    continue  outer;
                }
            }
            QueryWrapper qw = new QueryWrapper();
            qw.eq("name",oldTaglist[i]);
            Label label = labelDao.selectOne(qw);
            redisService.remove("tagTimeAxis/"+label.getId());
            if(label.getNum()==1){
                labelDao.deleteById(label.getId());
            }else {
                UpdateWrapper uuw = new UpdateWrapper();
                uuw.eq("id",label.getId());
                uuw.set("num",label.getNum()-1);
                labelDao.update(label,uuw);
            }
        }
            //新增标签
           newTag:for (int i = 0 ; i < newTaglist.length ; i++ ){
                for (int j = 0; j <oldTaglist.length ; j++) {
                    if(newTaglist[i]==oldTaglist[j]){
                        //新标签的内容在老标签中找不到，说明是新加的
                        continue  newTag;
                    }
                }
                QueryWrapper qw = new QueryWrapper();
                qw.eq("name",newTaglist[i]);
                Label label = labelDao.selectOne(qw);
                if(Objects.isNull(label)){
                    Label label1 = new Label();
                    label1.setName(newTaglist[i]);
                    label1.setDisplay("0");
                    label1.setNum(1);
                    labelDao.insert(label1);
                }else {
                    UpdateWrapper taguw = new UpdateWrapper();
                    taguw.eq("id",label.getId());
                    taguw.set("num",label.getNum()+1);
                    labelDao.update(label,taguw);
                    redisService.remove("tagTimeAxis/"+label.getId());
                }
            }
            String category = articls.getCategory();
             if(oldArticls.getCategory()!=category){
                 //老分类减去1
                 QueryWrapper qw = new QueryWrapper();
                 qw.eq("categories",oldArticls.getCategory());
                 Categories categories = categoriesDao.selectOne(qw);
                 UpdateWrapper uww = new UpdateWrapper();
                 uww.eq("id",categories.getId());
                 uww.set("num",categories.getNum()-1);
                 categoriesDao.update(categories,uww);

                 //新分类加1
                 QueryWrapper qww = new QueryWrapper();
                 qww.eq("categories",category);
                 Categories mycategories = categoriesDao.selectOne(qww);
                 if(Objects.isNull(mycategories)){
                     //如果没有，创建一个分类
                     Categories categories1 = new Categories();
                     categories1.setCategories(category);
                     categories1.setDisplay("0");
                     categories1.setNum(1);
                     categoriesDao.insert(categories1);
                 }else{
                     UpdateWrapper uuw = new UpdateWrapper();
                     uuw.eq("id",mycategories.getId());
                     uuw.set("num",mycategories.getNum()+1);
                     categoriesDao.update(mycategories,uuw);
                     redisService.remove("categorieTimeAxis/"+categories.getId());
                 }
             }

            UpdateWrapper uw = new UpdateWrapper();
            uw.eq("id",articls.getId());
            uw.set("title",articls.getTitle());
            uw.set("html",articls.getHtml());
            uw.set("category",articls.getCategory());
            uw.set("tags",articls.getTags());
            uw.set("markdown",articls.getMarkdown());
            int update = articlsDao.update(articls, uw);
            if(update<=0){
                return false;
            }

            //删除时间轴缓存
            redisService.remove("acticlsTimeAxis");
            //删除分页缓存
            redisService.remove("getArticlsByPage");
            //删除文章id缓存
            redisService.hdel("getArticlsById",articls.getId().toString());
             return  true;
    }
    public Map getBrowseAndLike(Integer id){
        QueryWrapper qw = new QueryWrapper();
        qw.select("browse","likearticls");
        qw.eq("id",id);
        Articls articls = articlsDao.selectOne(qw);
        if(Objects.isNull(articls)){
            return  null;
        }
        Map data = new HashMap();
        data.put("browse",articls.getBrowse());
        data.put("likearticls",articls.getLikearticls());
        return  data;
    }






}
