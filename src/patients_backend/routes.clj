(ns patients-backend.routes
  (:require [patients-backend.clients-handlers :refer :all]))

(def clients-routes
  [["/clients" {:get  get-clients
                ;;:post post-client
                }]])

