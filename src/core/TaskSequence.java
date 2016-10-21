package core;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class TaskSequence extends SpecialTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = (Logger) LoggerFactory.getLogger(TaskSequence.class);
	
	private Task nextTask;

	public TaskSequence(String name, String description, Project father, ArrayList<Activity> root, Task nnextTask){
		super(name, description, father, root);
		nextTask = nnextTask;
	}
	
	@Override
	public String getName(){
		String myName = super.getName() + " task sequence.";
		return myName;
	}
	
	@Override
	public void stopTask() {
		Clock clock = Clock.getInstance();
		if (nextTask == null){
			log.info("Stopping the task: " + getName() + " with description: " + getDescription());
			int i = 0;
			i = getIntervalList().size() - 1;
			clock.deleteObserver(getIntervalList().get(i));
			Project p = this.getFather();
			
			while (p!=null){
				SerializeData.saveData(p, "tempState");
				p = p.getFather();
			}
			log.info("Task: " + getName() + " stopped");
		} else {
			nextTask.startTask(nextTask.getName(), nextTask.getDescription());
			log.info("Stopping the task: " + getName() + " with description: " + getDescription());
			int i = 0;
			i = getIntervalList().size() - 1;
			clock.deleteObserver(getIntervalList().get(i));
			Project p = this.getFather();
			
			while (p!=null){
				SerializeData.saveData(p, "tempState");
				p = p.getFather();
			}
			log.info("Task: " + getName() + " stopped");
		}
	}
	
	@Override
	public Task getNextTask() {
		return nextTask;
	}

	@Override
	public Date getTaskStartDate() {
		return null;
	}

	@Override
	public int getTimeLimit() {
		return 0;
	}
}