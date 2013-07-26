(ns org.tobereplaced.jdbc-pool-test
  (:require (clojure (test :refer [deftest is]))
            (clojure.java (jdbc :refer [query]))
            (org.tobereplaced (jdbc-pool :refer [pool]))))

;; This runs on a local development db I have set up.  This will
;; almost certainly fail for you.  I felt it was more important to
;; make sure it runs over an actual db.  If you have ideas on how to
;; test this more appropriately, please speak up in the issues.
(deftest pool-test
  (with-open [db (pool {:subprotocol "postgresql"
                        :subname "//localhost:5432/test"
                        :classname "org.postgresql.Driver"
                        :user "test"
                        :password "test"})]
    (is (query db ["SELECT table_name FROM information_schema.tables"])
        "should be usable with clojure.java.jdbc")))
