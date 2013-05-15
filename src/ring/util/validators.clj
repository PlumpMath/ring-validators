
(ns ring.util.validators
  (:require [dire.core :refer [with-precondition! with-handler!]]))

(defn- value [param req]
  (get-in req [:params param] ""))

;; Public
;; ------

(defn with-validations
  "Attach validations to the specified function"
  [func & body]
  (let [all-args (apply hash-map body)
        val-args (dissoc all-args :on-error)]
    (doseq [validator (keys val-args)
            failure-message (vals val-args)]
      (let [identifier (keyword (gensym))]
        (with-precondition! func
          identifier
          validator)
        (with-handler! func
          {:precondition identifier}
          (partial (or (:on-error all-args)
                       (fn [e & args]))
                   failure-message))))))

(defn param-exists?
  "Validates a parameter exists and is not blank"
  [param]
  (fn [req]
    (> (count (value param req)) 0)))

(defn param-int?
  "Validates a parameter is an integer"
  [param]
  (fn [req]
    (boolean
      (try
        (let [v (value param req)]
          (or (integer? v)
              (Integer/parseInt v)))
        (catch Exception e)))))

