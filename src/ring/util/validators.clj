
(ns ring.util.validators)

(defn- value [param req]
  (get-in req [:params param] ""))

;; Public
;; ------

(defn param-exists?
  "Validates a parameter exists and is not blank"
  [param req]
  (> (count (value param req)) 0))

(defn param-int?
  "Validates a parameter is an integer"
  [param req]
  (integer? (value param req)))

