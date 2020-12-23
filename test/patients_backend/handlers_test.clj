(ns patients-backend.handlers-test
  (:require [patients-backend.core :refer [connect-to-db]]
            [clojure.test :refer :all]
            [toucan.util.test :as tt]
            [patients-backend.models.patient :refer [Patient]]
            [patients-backend.clients-handlers :as src]))

(def rudolf-habsburg {:full_name "Rudolf von Habsburg"
                      :sex "m"
                      :date_of_birth (java.sql.Date/valueOf "1218-05-01")
                      :address "Freiburg"
                      :omi_policy_number "321123"})

(defn- equals?
  "For comparing maps with toucan instances"
  [& args] 
  (= (map (comp vals #(:dissoc % :id)) args)))

;; TODO: use 'use-fixtures' for db connection

(deftest get-test
  (testing "get-clients"
    (connect-to-db)
    (tt/with-temp Patient [rudolf rudolf-habsburg]
      (let [result (-> (src/get-clients 0)
                       (:body)
                       (second))]
        (is (equals? rudolf-habsburg result))))))

(deftest post-test
  (testing "post-client"
    (connect-to-db)
    #_(this is up to me)))
