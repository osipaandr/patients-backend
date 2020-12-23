(ns patients-backend.core
  (:require [toucan.db :as db]
            [toucan.models :as m]
            [patients-backend.models.patient :refer [Patient]]
            [patients-backend.routes :refer [clients-routes]]
            [reitit.ring :as r]
            [ring.middleware.json :refer [wrap-json-response,
                                          wrap-json-body]]
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
   (r/router clients-routes
             {:data {:middleware [wrap-json-response,
                                  wrap-json-body]}})))

(defn -main
  [& args]
  (connect-to-db)
  #_(jetty/run-jetty #'app {:port 3000})
  (run-server #'app {:port 3000, :join? false}))

(comment (app {:request-method :get
               :uri "/clients"}))
