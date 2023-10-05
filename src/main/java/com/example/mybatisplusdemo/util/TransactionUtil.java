package com.example.mybatisplusdemo.util;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 事务工具类
 *
 * @author Oriental Ming
 * @date 2023/10/5 16:47
 */
@Configuration
@RequiredArgsConstructor
public class TransactionUtil {

	private final TransactionTemplate template;

	/**
	 * 手动执行事务, 编程式事务
	 *
	 * @param task 执行任务，并自定义返回值
	 */
	public void execute(Runnable task) {
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				task.run();
			}
		});
	}

	/**
	 * 手动执行事务, 编程式事务
	 *
	 * @param task 执行任务，并自定义返回值
	 * @return U 自定义返回对象
	 */
	public <U> U execute(Supplier<U> task) {
		return template.execute(transactionStatus -> task.get());
	}

	/**
	 * 自定义异常处理，编程式事务
	 *
	 * @param task 执行任务，并自定义返回值
	 */
	public void execute(Runnable task, Consumer<Throwable> rollbackOpt) {
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(@NotNull TransactionStatus status) {
				try {
					task.run();
				} catch (Throwable e) {
					status.setRollbackOnly();
					rollbackOpt.accept(e);
				}
			}
		});
	}

}
