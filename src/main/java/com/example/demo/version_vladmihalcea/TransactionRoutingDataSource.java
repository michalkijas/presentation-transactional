package com.example.demo.version_vladmihalcea;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

    enum DataSourceType {
        READ_ONLY, READ_WRITE
    }


    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        return readOnly ? DataSourceType.READ_ONLY : DataSourceType.READ_WRITE;
    }

}
