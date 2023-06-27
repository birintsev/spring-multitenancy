package com.github.birintsev.example.multitenancy.datarouting.mongodb;

import com.mongodb.ClientSessionOptions;
import com.mongodb.client.*;
import com.mongodb.connection.ClusterDescription;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public abstract class DelegatingMongoClient implements MongoClient {

    protected abstract MongoClient getClientForDelegation();

    @Override
    public MongoDatabase getDatabase(String databaseName) {
        return getClientForDelegation().getDatabase(databaseName);
    }

    @Override
    public ClientSession startSession() {
        return getClientForDelegation().startSession();
    }

    @Override
    public ClientSession startSession(ClientSessionOptions options) {
        return getClientForDelegation().startSession(options);
    }

    @Override
    public void close() {
        getClientForDelegation().close();
    }

    @Override
    public MongoIterable<String> listDatabaseNames() {
        return getClientForDelegation().listDatabaseNames();
    }

    @Override
    public MongoIterable<String> listDatabaseNames(ClientSession clientSession) {
        return getClientForDelegation().listDatabaseNames(clientSession);
    }

    @Override
    public ListDatabasesIterable<Document> listDatabases() {
        return getClientForDelegation().listDatabases();
    }

    @Override
    public ListDatabasesIterable<Document> listDatabases(ClientSession clientSession) {
        return getClientForDelegation().listDatabases(clientSession);
    }

    @Override
    public <TResult> ListDatabasesIterable<TResult> listDatabases(Class<TResult> tResultClass) {
        return getClientForDelegation().listDatabases(tResultClass);
    }

    @Override
    public <TResult> ListDatabasesIterable<TResult> listDatabases(ClientSession clientSession, Class<TResult> tResultClass) {
        return getClientForDelegation().listDatabases(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch() {
        return getClientForDelegation().watch();
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> tResultClass) {
        return getClientForDelegation().watch(tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
        return getClientForDelegation().watch(pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        return getClientForDelegation().watch(pipeline, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
        return getClientForDelegation().watch(clientSession);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> tResultClass) {
        return getClientForDelegation().watch(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
        return getClientForDelegation().watch(clientSession, pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        return getClientForDelegation().watch(clientSession, pipeline, tResultClass);
    }

    @Override
    public ClusterDescription getClusterDescription() {
        return getClientForDelegation().getClusterDescription();
    }
}
