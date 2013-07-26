(ns org.tobereplaced.jdbc-pool
  "C3P0 connection pooling for org.clojure/java.jdbc ."
  (:require [camel-snake-kebab :refer [->CamelCase]]
            (org.tobereplaced (mapply :refer [mapply])))
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource)
           (java.io Closeable)))

(defn- connection-pool
  "Returns a C3PO connection pool for a database specification and
  optional options."
  ^com.mchange.v2.c3p0.ComboPooledDataSource
  [db & {:as options}]
  (let [{:keys [subprotocol subname classname user password]} db
        properties (merge {:driver-class classname
                           :jdbc-url (str "jdbc:" subprotocol ":" subname)
                           :user user
                           :password password}
                          options)
        setter (fn [k] (->> k ->CamelCase name (str ".set") symbol))
        commands (map (fn [[k v]] (list (setter k) v)) properties)]
    (eval `(doto (ComboPooledDataSource.) ~@commands))))

(defrecord JDBCPool [^ComboPooledDataSource datasource]
  Closeable
  (close [this] (.close datasource)))

(defn pool
  "Returns a jdbc-specification that contains :datasource set to a
  connection pool for a database specification and optional options.
  The resulting specification is closeable and of type
  org.tobereplaced.jdbc_pool.JDBCPool.  Options may be set to anything
  that has a corresponding setter for the C3P0 connection pool.

  Ex:

  (pool db :max-statements 10)."
  ^org.tobereplaced.jdbc_pool.JDBCPool
  [db & {:as options}]
  (->JDBCPool (mapply connection-pool db options)))
