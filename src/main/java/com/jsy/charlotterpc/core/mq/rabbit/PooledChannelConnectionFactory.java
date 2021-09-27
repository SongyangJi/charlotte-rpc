package com.jsy.charlotterpc.core.mq.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.experimental.Delegate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */
public class PooledChannelConnectionFactory extends RabbitConnectionFactory{

    Connection connection;

    public PooledChannelConnectionFactory(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public synchronized Connection createConnection() throws IOException, TimeoutException {
        if (this.connection == null || !this.connection.isOpen()) {
            connection = new ConnectionWrapper(bareConnectionFactory.newConnection());
        }
        return connection;
    }

    private static class ConnectionWrapper implements com.rabbitmq.client.Connection {
        @Delegate
        Connection delegate;

        public ConnectionWrapper(Connection delegate) {
            this.delegate = delegate;
        }
    }

}
