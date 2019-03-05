package cn.bin2.sport.common.util;

import afu.org.checkerframework.checker.oigj.qual.O;
import cn.bin2.sport.core.job.IJobHandle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 8:54 2019/3/5
 * @Modified By:
 */
public class JobHandleExec implements ApplicationContextAware {

    private static ConcurrentHashMap<String,IJobHandle> jobHandleRepostiory = new ConcurrentHashMap<>();
    private static IJobHandle putJobHandle(String str,IJobHandle jobHandle){
        return jobHandleRepostiory.put(str,jobHandle);
    }
    public static IJobHandle loadJobHandle(String str){
        return jobHandleRepostiory.get(str);
    }

    public void start(){

        initJobRepository(applicationContext);
    }
    private void initJobRepository(ApplicationContext context){
        if(context==null)
            return;
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(JobHandle.class);
        for(Map.Entry<String,Object> entry : beansWithAnnotation.entrySet()){
            Object clazzName = entry.getValue();
            IJobHandle handle =(IJobHandle)clazzName;
            String name =  clazzName.getClass().getAnnotation(JobHandle.class).value();
            if(loadJobHandle(name)!=null)
                throw new RuntimeException("name conficts");
            putJobHandle(entry.getKey(),handle);
        }
    }

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
