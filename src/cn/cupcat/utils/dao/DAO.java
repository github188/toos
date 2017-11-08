package cn.cupcat.utils.dao;

import system.lib.DataBase;
import system.lib.PageQueryInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xy on 2017/10/11.
 * @author zxy
 * @version 1.0
 * @tel 178-5310-0679
 * @QQ  1227387823
 *
 *
 * 定义通用操作数据库方法，其中类上泛型T表示数据库表主键对应的java类型
 */
public interface DAO<T> {

    /**
     * 添加
     * @param paramMap      参数Map。其中key为列名；value为列对应的值
     * @return boolean      true-成功  false-失败
     * */
    boolean add(Map<String, Object> paramMap);
    /**
     *  添加：
     *  @param paramMap      其中key为列名；value为列对应的值
     *  @param flag          暂时没有实际意义；true和false都可以；为了不和上面add重复
     *  @return  int         返回 添加数据的主键值
     * */
    int add(Map<String, Object> paramMap, boolean flag);

    /**
     * 修改： 说明：该方法默认使用主键列名为 ID
     * @param paramMap      其中key为列名；value为列对应的值
     * @param id            泛型：表的主键id对应的java类型，一般在model层继承BasicDAO的时候指定
     * @return boolean      true-成功  false-失败
     * */
    boolean edit(Map<String, Object> paramMap, T id);

    /**
     * 修改
     * @param paramMap      其中key为列名；value为列对应的值
     * @param id            泛型：表的主键id对应的java类型，一般在model层继承BasicDAO的时候指定
     * @param PKColumnName  指定表的主键列名，若不指定（传入：null或者""），则默认为ID
     * @return boolean      true-成功  false-失败
     * */
    boolean edit(Map<String, Object> paramMap, T id, String PKColumnName);


    /**
     * 单个删除,主键列名默认为ID
     * @param id
     * @return boolean      true-成功  false-失败
     * */
    boolean delete(T id);
    /**
     *  单个删除，需要指定主键列名
     *  @param id
     *  @param PKColumnName 指定要删除表的主键列名
     *  @return boolean      true-成功  false-失败
     * */
    boolean delete(T id, String PKColumnName);


    /**
     *  单个标记删除，实质上是修改
     *  @param id
     *  @param statusColumnName     标记字段名
     *  @param statusValue          标记字段值
     * */
    boolean markDelete(T id, String statusColumnName, String statusValue);


    /**
     *  批量删除
     *  @param idArray  ID的数组
     *  @return boolean      true-成功  false-失败
     * */
    boolean batchDelete(String[] idArray);
    /**
     *  @param ids      ID字符串；例如："1,2,3"
     * */
    boolean batchDelete(String ids);
    /**
     *  @param idArray      ID的数组
     *  @param PKColumnName 表的主键列名
     * */
    boolean batchDelete(String[] idArray, String PKColumnName);
    /**
     *  @param ids      ID字符串；例如："1,2,3"
     *  @param PKColumnName 表的主键列名
     * */
    boolean batchDelete(String ids, String PKColumnName);


    /**
     *  批量标记删除
     *
     *  @param ids
     *  @param statusColumnName     标记字段名
     *  @param statusValue          标记字段值
     *
     * */
    boolean markBatchDelete(String ids, String statusColumnName, String statusValue);

    /**
     *  获得单条
     *  @param id
     * */
    HashMap<String,Object> getOne(T id);
    /**
     *  @param id
     *  @param PKColumnName 表的主键列名
     * */
    HashMap<String,Object> getOne(T id, String PKColumnName);

    /**
     * 获取全部数据，查询全部列
     * */
    ArrayList<HashMap<String,Object>> getAll();
    /**
     * 获取指定列的全部数据，
     *  @param columns 若出入null或者长度为0，则查询全部列
     * */
    ArrayList<HashMap<String,Object>> getAll(String[] columns);
    /**
     * 获取指定列符合过滤条件数据
     *  @param columns      若出入null或者长度为0，则查询全部列
     *  @param paramMap     key为列名，value为该列对应的模糊查询值
     *  @param customSQL    自定义SQL为where子句的过滤条件；不如需可以传入null；注意：必须指定SQL连接符：例如 "AND ...."
     *  @param SQLConnector 定义SQL连接符用于指定paramMap中各条件之间的连接关系
     * */
    ArrayList<HashMap<String,Object>> getAll(String[] columns, Map<String, String> paramMap, String customSQL, String SQLConnector);
    /**
     *  获取前 n 项 数据
     * @param paramMap      参数Map。其中key为列名；value为列对应的值；查询为模糊查询
     *  @param n            指定查询前多少条数据
     * */
    ArrayList<HashMap<String,Object>> getTopN(Map<String, Object> paramMap, Integer n);
    /**
     *  获取指定列前 n 项 数据
     * */
    ArrayList<HashMap<String,Object>> getTopN(String[] columns, Map<String, Object> paramMap, Integer n);

    /**
     * 获取查询数量
     * @param columnArray   数据库表列的数组
     * @param valueArray    前台传来值的数组
     * @return String     符合条件的数量
     * */
    String getCount(String[] columnArray, String[] valueArray);

    /**
     * 批量添加
     * @param list 传入的列表
     * @return
     */
    boolean addBatch(ArrayList<HashMap<String, Object>> list);
}
