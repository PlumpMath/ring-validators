
(ns ring.util.validators
  (:require [dire.core :refer [with-precondition! with-handler!]]))

(defn- param-value [param req]
  (get-in req [:params param] ""))

(defmacro defparser [func doc & body]
  `(defn ~func [param#]
     (fn [req#]
       (boolean
         (try
           (let [~'value (str (param-value param# req#))]
             (do ~@body))
           (catch Exception e#))))))

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
    (> (count (param-value param req)) 0)))

(defparser param-int?
  "Validates a parameter is a valid integer"
  (Integer/parseInt value))

(defparser param-long?
  "Validates a parameter is a valid long"
  (Long/parseLong value))

