/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/** @module vertx-mysql-postgresql-js/async_sql_service */
var utils = require('vertx-js/util/utils');
var SqlConnection = require('vertx-sql-js/sql_connection');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JAsyncSqlService = io.vertx.ext.asyncsql.AsyncSqlService;

/**

 Represents an asynchronous MySQL or PostgreSQL service.

 @class
*/
var AsyncSqlService = function(j_val) {

  var j_asyncSqlService = j_val;
  var that = this;

  /**
   Called to start the service

   @public
   @param whenDone {function} 
   */
  this.start = function(whenDone) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_asyncSqlService.start(function(ar) {
      if (ar.succeeded()) {
        whenDone(null, null);
      } else {
        whenDone(null, ar.cause());
      }
    });
    } else utils.invalidArgs();
  };

  /**
   Called to stop the service

   @public
   @param whenDone {function} 
   */
  this.stop = function(whenDone) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_asyncSqlService.stop(function(ar) {
      if (ar.succeeded()) {
        whenDone(null, null);
      } else {
        whenDone(null, ar.cause());
      }
    });
    } else utils.invalidArgs();
  };

  /**
   Returns a connection that can be used to perform SQL operations on. It's important to remember to close the
   connection when you are done, so it is returned to the pool.

   @public
   @param handler {function} the handler which is called when the <code>JdbcConnection</code> object is ready for use. 
   */
  this.getConnection = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_asyncSqlService.getConnection(function(ar) {
      if (ar.succeeded()) {
        handler(new SqlConnection(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else utils.invalidArgs();
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_asyncSqlService;
};

/**
 Create a MySQL service

 @memberof module:vertx-mysql-postgresql-js/async_sql_service
 @param vertx {Vertx} the Vert.x instance 
 @param config {Object} the config 
 @return {AsyncSqlService} the service
 */
AsyncSqlService.createMySqlService = function(vertx, config) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'object') {
    return new AsyncSqlService(JAsyncSqlService.createMySqlService(vertx._jdel, utils.convParamJsonObject(config)));
  } else utils.invalidArgs();
};

/**
 Create a PostgreSQL service

 @memberof module:vertx-mysql-postgresql-js/async_sql_service
 @param vertx {Vertx} the Vert.x instance 
 @param config {Object} the config 
 @return {AsyncSqlService} the service
 */
AsyncSqlService.createPostgreSqlService = function(vertx, config) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'object') {
    return new AsyncSqlService(JAsyncSqlService.createPostgreSqlService(vertx._jdel, utils.convParamJsonObject(config)));
  } else utils.invalidArgs();
};

/**
 Create an event bus proxy to a service which lives somewhere on the network and is listening on the specified
 event bus address

 @memberof module:vertx-mysql-postgresql-js/async_sql_service
 @param vertx {Vertx} the Vert.x instance 
 @param address {string} the address on the event bus where the service is listening 
 @return {AsyncSqlService} 
 */
AsyncSqlService.createEventBusProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return new AsyncSqlService(JAsyncSqlService.createEventBusProxy(vertx._jdel, address));
  } else utils.invalidArgs();
};

// We export the Constructor function
module.exports = AsyncSqlService;