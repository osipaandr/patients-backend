(ns patients-backend.routes
  (:require [patients-backend.clients-handlers :refer :all]))

(def clients-routes
  [["/clients" {:get get-clients}]
   ["/client" {:post   post-client
               :delete delete-client
               :patch  patch-client}]])

