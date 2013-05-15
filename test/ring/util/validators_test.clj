
(ns ring.util.validators
  (:require [clojure.test :refer :all]
            [ring.util.validators :refer :all]))

(deftest parameter-exists
  (is (param-exists? :foo {:params {:foo "abd"}}))
  (is (not (param-exists? :foo {:params {}}))))

(deftest parameter-is-an-integer
  (is (param-int? :foo {:params {:foo 123}}))
  (is (param-int? :foo {:params {:foo "123"}}))
  (is (not (param-int? :foo {:params {:foo "abc"}})))
  (is (not (param-int? :foo {:params {}}))))

