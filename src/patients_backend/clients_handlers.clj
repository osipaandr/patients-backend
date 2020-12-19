(ns patients-backend.clients-handlers
  (:require [toucan.db :as db]
            [clojure.data.json :as json]
            [patients-backend.models.patient :refer [Patient]]))

(defn date-parser
  [_ value]
  (if (= java.sql.Date (class value))
    (.toString value)
    value))

(defn ok
  [body]
  {:status 200
   :body body})

(defn to-json
  [x]
  (json/write-str x
                  :value-fn date-parser))

(defn get-clients
  [_]
  (->> (db/select Patient)
       (to-json)
       (ok)))

(defn post-client
  [_]
  (print "Not implemented yet"))
