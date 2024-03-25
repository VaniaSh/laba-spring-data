package ua.kpi.its.lab.data

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ua.kpi.its.lab.data.config.Config
import ua.kpi.its.lab.data.entity.Train
import ua.kpi.its.lab.data.svc.EntityService
import ua.kpi.its.lab.data.svc.impl.TrainServiceImpl

object Main {
    @Autowired
    @Qualifier(value = "trainServiceImpl")
    private lateinit var entityService :EntityService;
    @JvmStatic
    fun main(args: Array<String>) {


        val context = AnnotationConfigApplicationContext(Config::class.java)

        entityService = context.getBean(TrainServiceImpl::class.java)

        val listOfEntities = context.getBean(InitEntityExamples::class.java)
        listOfEntities.initTrains()
        entityService.findById<Train>(3);
        entityService.delete(4)


    }

}