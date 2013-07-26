(ns jdbc-pool-usage
  (:require (clojure.java (jdbc :refer [query]))
            (org.tobereplaced (jdbc-pool :refer [pool]))))

(def specification
  "A jdbc-compatible specification."
  {:subprotocol "postgresql"
   :subname "//localhost:5432/test"
   :classname "org.postgresql.Driver"
   :user "test"
   :password "test"})

;;; You can use custom options, or accept the C3P0 defaults.
(with-open [db (pool specification :max-statements 10 :max-pool-size 33)]
  (query db ["SELECT table_name FROM information_schema.tables"]))
