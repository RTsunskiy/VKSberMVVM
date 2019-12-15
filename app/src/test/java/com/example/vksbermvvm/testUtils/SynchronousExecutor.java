package com.example.vksbermvvm.testUtils;


import java.util.concurrent.Executor;

/**
 * Класс для того, чтобы в тесте все операции запускались синхронно
 *
 * @author Цунский Роман on 2019-12-15
 */
public class SynchronousExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }

}