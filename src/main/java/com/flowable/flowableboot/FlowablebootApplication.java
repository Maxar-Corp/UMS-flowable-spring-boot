package com.flowable.flowableboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
		createUMSTasks(importFile).forEach(testUmsTask -> umsTaskService.createUmsTask(
				testUmsTask.getProcess_instance_id(),
				testUmsTask.getName(),
				testUmsTask.getRequester(),
				testUmsTask.getAssignee(),
				testUmsTask.getPriority(),
				testUmsTask.getDueDate(),
				testUmsTask.getReceivedDate(),
				testUmsTask.getLoe(),
				testUmsTask.getStatus(),
				testUmsTask.getDescription()
		)); ;
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

