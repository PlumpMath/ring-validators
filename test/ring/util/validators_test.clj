
(ns ring.util.validators
  (:require [clojure.test :refer :all]
            [ring.util.validators :refer :all]))

(deftest parameter-exists
  (is ((param-exists? :foo) {:params {:foo "abd"}}))
  (is (not ((param-exists? :foo) {:params {}}))))

(deftest parameter-is-an-integer
  (is ((param-int? :foo) {:params {:foo 123}}))
  (is ((param-int? :foo) {:params {:foo "123"}}))
  (is (not ((param-int? :foo) {:params {:foo "abc"}})))
  (is (not ((param-int? :foo) {:params {}}))))

(deftest parameter-is-a-long
  (is ((param-long? :foo) {:params {:foo 17592186045509}}))
  (is ((param-long? :foo) {:params {:foo "17592186045509"}}))
  (is (not ((param-long? :foo) {:params {:foo "asdasdasd"}})))
  (is (not ((param-long? :foo) {:params {}}))))

;(run-tests)

