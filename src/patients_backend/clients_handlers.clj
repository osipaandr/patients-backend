(ns patients-backend.clients-handlers
  (:require [toucan.db :as db]
            [patients-backend.models.patient :refer [Patient]]))

(defn wrap-status
  [code]
  (fn [body]
    {:status code
     :body body}))

(def ok (wrap-status 200))
(def created (wrap-status 201))

(defn get-clients
  [_]
  (ok (db/select Patient)))

(defn post-client
  [req]
  (created (db/insert! Patient (:body req))))

(defn delete-client
  [req]
  (print "Not implemented yet"))

(defn patch-client
  [req]
  (print "Not implemented yet"))
