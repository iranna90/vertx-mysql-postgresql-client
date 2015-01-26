/*
* Copyright 2014 Red Hat, Inc.
*
* Red Hat licenses this file to you under the Apache License, version 2.0
* (the "License"); you may not use this file except in compliance with the
* License. You may obtain a copy of the License at:
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations
* under the License.
*/

package io.vertx.ext.asyncsql.mysql;

import io.vertx.ext.asyncsql.mysql.MysqlService;
import io.vertx.core.Vertx;
import io.vertx.core.Handler;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.util.ArrayList;import java.util.HashSet;import java.util.List;import java.util.Map;import java.util.Set;import java.util.UUID;
import io.vertx.serviceproxy.ProxyHelper;
import io.vertx.serviceproxy.ProxyHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.SelectOptions;
import io.vertx.ext.asyncsql.mysql.MysqlConnection;
import io.vertx.ext.asyncsql.BaseSqlService;
import io.vertx.ext.asyncsql.mysql.MysqlService;
import io.vertx.core.json.JsonArray;
import java.util.List;
import io.vertx.ext.asyncsql.DatabaseCommands;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.asyncsql.mysql.MysqlTransaction;

/*
  Generated Proxy code - DO NOT EDIT
  @author Roger the Robot
*/
public class MysqlServiceVertxProxyHandler extends ProxyHandler {

  private final Vertx vertx;
  private final MysqlService service;
  private final String address;

  public MysqlServiceVertxProxyHandler(Vertx vertx, MysqlService service, String address) {
    this.vertx = vertx;
    this.service = service;
    this.address = address;
  }

  public void handle(Message<JsonObject> msg) {
    JsonObject json = msg.body();
    String action = msg.headers().get("action");
    if (action == null) {
      throw new IllegalStateException("action not specified");
    }
    switch (action) {
      case "start": {
        service.start(createHandler(msg));
        break;
      }
      case "stop": {
        service.stop(createHandler(msg));
        break;
      }
      case "raw": {
        service.raw((java.lang.String)json.getValue("command"), createHandler(msg));
        break;
      }
      case "insert": {
        service.insert((java.lang.String)json.getValue("table"), convertList(json.getJsonArray("fields").getList()), convertList(json.getJsonArray("values").getList()), createHandler(msg));
        break;
      }
      case "select": {
        service.select((java.lang.String)json.getValue("table"), new io.vertx.ext.asyncsql.SelectOptions(json.getJsonObject("options")), createHandler(msg));
        break;
      }
      case "prepared": {
        service.prepared((java.lang.String)json.getValue("statement"), (io.vertx.core.json.JsonArray)json.getValue("values"), createHandler(msg));
        break;
      }


      case "begin": {
        service.begin(res -> {
          if (res.failed()) {
            msg.fail(-1, res.cause().getMessage());
          } else {
            String proxyAddress = UUID.randomUUID().toString();
            ProxyHelper.registerService(MysqlTransaction.class, vertx, res.result(), proxyAddress);
            msg.reply(null, new DeliveryOptions().addHeader("proxyaddr", proxyAddress));
          }
        });
        break;
      }
      case "take": {
        service.take(res -> {
          if (res.failed()) {
            msg.fail(-1, res.cause().getMessage());
          } else {
            String proxyAddress = UUID.randomUUID().toString();
            ProxyHelper.registerService(MysqlConnection.class, vertx, res.result(), proxyAddress);
            msg.reply(null, new DeliveryOptions().addHeader("proxyaddr", proxyAddress));
          }
        });
        break;
      }
      default: {
        throw new IllegalStateException("Invalid action: " + action);
      }
    }
  }
  private <T> Handler<AsyncResult<T>> createHandler(Message msg) {
    return res -> {
      if (res.failed()) {
        msg.fail(-1, res.cause().getMessage());
      } else {
        msg.reply(res.result());
      }
    };
  }
  private <T> Handler<AsyncResult<List<T>>> createListHandler(Message msg) {
    return res -> {
      if (res.failed()) {
        msg.fail(-1, res.cause().getMessage());
      } else {
        msg.reply(new JsonArray(res.result()));
      }
    };
  }
  private <T> Handler<AsyncResult<Set<T>>> createSetHandler(Message msg) {
    return res -> {
      if (res.failed()) {
        msg.fail(-1, res.cause().getMessage());
      } else {
        msg.reply(new JsonArray(new ArrayList<>(res.result())));
      }
    };
  }
  private Handler<AsyncResult<List<Character>>> createListCharHandler(Message msg) {
    return res -> {
      if (res.failed()) {
        msg.fail(-1, res.cause().getMessage());
      } else {
        JsonArray arr = new JsonArray();
        for (Character chr: res.result()) {
          arr.add((int)chr);
        }
        msg.reply(arr);
      }
    };
  }
  private Handler<AsyncResult<Set<Character>>> createSetCharHandler(Message msg) {
    return res -> {
      if (res.failed()) {
        msg.fail(-1, res.cause().getMessage());
      } else {
        JsonArray arr = new JsonArray();
        for (Character chr: res.result()) {
          arr.add((int)chr);
        }
        msg.reply(arr);
      }
    };
  }
  private <T> Map<String, T> convertMap(Map map) {
    return (Map<String, T>)map;
  }
  private <T> List<T> convertList(List list) {
    return (List<T>)list;
  }
  private <T> Set<T> convertSet(List list) {
    return new HashSet<T>((List<T>)list);
  }
}