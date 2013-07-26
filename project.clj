(defproject org.tobereplaced/jdbc-pool "0.1.0"
  :description "C3P0 connection pooling for org.clojure/java.jdbc ."
  :url "https://github.com/ToBeReplaced/jdbc-pool"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [camel-snake-kebab "0.1.1"]
                 [org.tobereplaced/mapply "1.0.0"]]
  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.3.0-alpha4"]
                                  [postgresql/postgresql "9.1-901-1.jdbc4"]]}}
  :global-vars {*warn-on-reflection* true})
