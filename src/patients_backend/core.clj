(ns patients-backend.core
  (:require [toucan.db :as db]
            [toucan.models :as m]
            [patients-backend.models.patient :refer [Patient]]
            [patients-backend.routes :refer [clients-routes]]
            [reitit.ring :as r]
;;            [ring.adapter.jetty :as jetty]
            [org.httpkit.server :refer [run-server]])
  (:gen-class))

(def conn-conf {:classname   "org.postgresql.Driver"
                :subprotocol "postgresql"
                :subname     (System/getenv "DB_SUBNAME")
                :user        (System/getenv "DB_USER")
                :password    (System/getenv "DB_PASSWORD")})

(def app
  (r/ring-handler
   (r/router clients-routes)))

(defn -main
  [& args]
  (db/set-default-db-connection! conn-conf)
  (m/set-root-namespace! 'patients-backend.models.patient)
  #_(jetty/run-jetty #'app {:port 3000})
  (run-server #'app {:port 3000, :join? false}))

(comment (app {:request-method :get
               :uri "/clients"}))
