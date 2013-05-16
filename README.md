
# Ring Request Validators

Small library of functions which can be used to validate
[Ring](https://github.com/ring-clojure/ring) requests.

## Usage

Available from [Clojars](https://clojars.org/ring-validators)

```clojure
(ns my.project
  (:require [ring.util.validators :refer :all]))

; The functions return validators, so we need to create
; them and then execute them.

((param-exists? :foo) {:params {}}) ; => false

((param-int? :foo) {:params {:foo "123"}}) ; => true
```

Pretty boring, but these can then be attached to Ring handler
functions...

```clojure
(defn my-handler [req]
  ;; does something incredible, of course
  )

(with-validations 
  #'my-handler
  (param-exists? :foo) "You need to specify a foo"
  (param-int? :bar) "Bar needs to be an integer"
  :on-error
    (fn [message e & args]
      ;; create error response
      ))
```

## Validators

```clojure
(param-exists? :name)
(param-int? :name)
(param-long? :name)
```

## Disclaimer

Just playing with some ideas, might not work out in practice.

