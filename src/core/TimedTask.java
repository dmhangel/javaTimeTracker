package core;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class TimedTask extends SpecialTask{
	
	private int timeLimit;

	private static Logger log = (Logger) LoggerFactory.getLogger(TimedTask.class);
	
	public TimedTask(String name, String description, Project father, ArrayList<Activity> root, int ntimeLimit) {
		super(name, description, father, root);
		timeLimit = ntimeLimit;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Task getNextTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getTaskStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTimeLimit() {
		
		return timeLimit;
	}

	@Override
	public void stopTask() {

		Clock clock = Clock.getInstance();
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
