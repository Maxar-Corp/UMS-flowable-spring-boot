package com.flowable.flowableboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flowable.flowableboot.dtos.UmsTaskPostDto;
import com.flowable.flowableboot.service.FlowableService;
import com.flowable.flowableboot.service.UmsTaskService;
import com.flowable.flowableboot.utils.generateUmsTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(proxyBeanMethods = false)
public class FlowablebootApplication implements CommandLineRunner{
	@Value("${ums.importfile}")
	private String importFile;

	@Autowired
	private UmsTaskService umsTaskService;

	public static void main(String[] args) {

		SpringApplication.run(FlowablebootApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final FlowableService flowableService) {
		return strings -> flowableService.createDemoUsers();
	}

	@Override
	public void run(String... args) throws Exception {

		for(generateUmsTasks.TestUmsTask testUmsTask: createUMSTasks(importFile)){
			UmsTaskPostDto tempPost = new UmsTaskPostDto();
			tempPost.setProcess_instance_id(testUmsTask.getProcess_instance_id());
			tempPost.setName(testUmsTask.getName());
			tempPost.setRequester(testUmsTask.getRequester());
			tempPost.setAssignee(testUmsTask.getAssignee());
			tempPost.setPriority(testUmsTask.getPriority());
			tempPost.setDueDate(testUmsTask.getDueDate());
			tempPost.setReceivedDate(testUmsTask.getReceivedDate());
			tempPost.setLoe(testUmsTask.getLoe());
			tempPost.setStatus(testUmsTask.getStatus());
			tempPost.setDescription(testUmsTask.getDescription());

			umsTaskService.createUmsTask(tempPost);
		}


		long numOfTasks = umsTaskService.total();
	}

	private List<generateUmsTasks.TestUmsTask> createUMSTasks(String fileToImport) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		List<generateUmsTasks.TestUmsTask> tasks = Arrays.asList(mapper.readValue(Paths
				.get(importFile).toFile(), generateUmsTasks.TestUmsTask[].class));

		return tasks;
	}
}

