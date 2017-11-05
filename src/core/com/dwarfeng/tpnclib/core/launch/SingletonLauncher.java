package com.dwarfeng.tpnclib.core.launch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.dwarfeng.dutil.basic.prog.ProcessException;
import com.dwarfeng.dutil.basic.prog.ProgramObverser;
import com.dwarfeng.dutil.basic.prog.RuntimeState;
import com.dwarfeng.dutil.develop.backgr.AbstractTask;
import com.dwarfeng.dutil.develop.backgr.Task;
import com.dwarfeng.tpnclib.core.control.TpncLib;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit.BackgroundType;

/**
 * 单例启动器。
 * <p>
 * 该启动器创建一个 {@link TpncLib} 实例，并启动； 当实例运行结束时，虚拟机随即退出。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class SingletonLauncher {

	public static final SingletonLauncher INSTANCE = new SingletonLauncher();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException ignore) {
			// 界面中的所有元素均支持这一外观，因此不可能出现异常。
		}

		try {
			INSTANCE.launch();
			INSTANCE.waitFinish();
			System.exit(INSTANCE.getExitCode());
		} catch (InterruptedException ignore) {
			// 中断也要按照基本法。
		}

	}

	private final TpncLib tpncLib;

	private final Lock lock = new ReentrantLock();

	private final Condition condition = lock.newCondition();
	private final ProgramObverser programObverser = new ProgramObverser() {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireRuntimeStateChanged(RuntimeState oldValue, RuntimeState newValue) {
			if (newValue.equals(RuntimeState.ENDED)) {
				exitCode = tpncLib.getToolkit().getExitCode();
				exitFlag = true;
				lock.lock();
				try {
					condition.signalAll();
				} finally {
					lock.unlock();
				}
			}
		}
	};
	private boolean exitFlag = false;

	private int exitCode = 0;

	/**
	 * 新实例。
	 */
	private SingletonLauncher() {
		tpncLib = new TpncLib();
		tpncLib.getToolkit().addProgramObverser(programObverser);
	}

	/**
	 * 获取退出代码。
	 * 
	 * @return 退出代码。
	 */
	public int getExitCode() {
		return exitCode;
	}

	/**
	 * 启动程序。
	 * <p>
	 * 该方法以异步的方式启动程序。
	 */
	public void launch() {
		Task task = new AbstractTask() {

			@Override
			protected void todo() throws Exception {
				try {
					tpncLib.getToolkit().start();
				} catch (ProcessException e) {
					exitFlag = true;
					exitCode = 12450;
				}
			}
		};

		tpncLib.getToolkit().submitTask(task, BackgroundType.CONCURRENT);
	}

	/**
	 * 等待程序运行结束。
	 * 
	 * @throws InterruptedException
	 *             等待过程中线程被外部中断。
	 */
	public void waitFinish() throws InterruptedException {
		lock.lock();
		try {
			while (!exitFlag) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

}