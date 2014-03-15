# jdbc-pool

WARNING: I am no longer using this library, and it should be
considered deprecated.  If you are looking for connection pooling,
consider using BoneCP or C3P0 directly.

C3P0 connection pooling for org.clojure/java.jdbc .

## Supported Clojure Versions

jdbc-pool is tested on Clojure 1.5.1 only.  It may work on other Clojure versions.

## Maturity

This is alpha quality software.

## Installation

jdbc-pool is available as a Maven artifact from [Clojars]:
```clojure
[org.tobereplaced/jdbc-pool "0.1.0"]
```
jdbc-pool follows [Semantic Versioning].  Please note that this means the public API for this library is not yet considered stable, and so it is subject to change.

## Documentation

The library exports one function, `pool`.  See its usage below.

C3P0 uses log4j as its logging backend.  You may want to consider adding the following to your project to use slf4j instead:
```clojure
[org.slf4j/log4j-over-slf4j "1.7.5"]
```

## Usage

```clojure
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
```

## Changelog

### v0.1.0

- Initial Release

## Support

Please post any comments, concerns, or issues to the Github issues page.

## License

Copyright © 2013 ToBeReplaced

Distributed under the Eclipse Public License, the same as Clojure.  The license can be found at epl-v10.html in the root of this distribution.

[Clojars]: http://clojars.org/org.tobereplaced/jdbc-pool
[Semantic Versioning]: http://semver.org
