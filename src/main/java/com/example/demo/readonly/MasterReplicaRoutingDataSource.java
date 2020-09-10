package com.example.demo.readonly;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.Map;

public class MasterReplicaRoutingDataSource extends AbstractRoutingDataSource {

    private enum Type {
        MASTER, REPLICA;
    }

    private static final ThreadLocal<Type> currentDataSource = new ThreadLocal<>();

    MasterReplicaRoutingDataSource(DataSource master, DataSource slave) {
        super.setDefaultTargetDataSource(master);
        super.setTargetDataSources(Map.of(
                Type.MASTER, master,
                Type.REPLICA, slave
        ));
    }


    static void setReadonlyDataSource(boolean isReadonly) {
        currentDataSource.set(isReadonly ? Type.REPLICA : Type.MASTER);
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return currentDataSource.get();
    }

//    @Override
//    protected Object determineCurrentLookupKey() {
//        boolean currentTransactionReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
//        return currentTransactionReadOnly ?
//                DataSourceType.READ_ONLY : DataSourceType.READ_WRITE;
//    }

}
