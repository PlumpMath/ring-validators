
# Ring Request Validators

Small library of functions which can be used to validate
[Ring](https://github.com/ring-clojure/ring) requests.

## Usage

Available from [Clojars](https://clojars.org/ring-validators)

```clojure
(ns my.project
  (:require [ring.util.validators :refer :all]))

(param-exists? :foo {:params {}}) ; => false

(param-int? :foo {:params {:foo "123"}}) ; => true
```

