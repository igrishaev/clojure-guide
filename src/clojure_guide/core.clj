(ns clojure-guide.core)

(time
 (doseq [a [1 2 3]]
   (let [b (* a a)]
     (println b)
     )
   )
 )

(time
 (doseq [a [1 2 3]]
   (let [b (* a a)]
     (println b))))





(def mapping {
   :name "Ivan"
   :email "test@test.com"
})




(def numbers [
  1,
  2,
  3
])



1


(ns some.ns
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))


(ns some.ns
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))


(ns some.ns
  (:require
   [clojure [edn :as edn]
            [walk :as walk]
            [string :as str]]))


(ns some.ns
  (:require
   [clojure.walk :as clojure.walk]
   [cool.utils.walk :as utils.walk]))


(ns some.ns
  (:require
   [clojure.string :as s]))

(s/starts-with? ...)

(ns some.ns
  (:require
   [clojure.string :as str]))

(str/starts-with? ...)

(ns some.ns
  (:use clojure.walk))


(ns project.core
  (:require
   [cheshire.core :as json]
   [clojure.java.io :as io]
   [clojure.java.jdbc :as jdbc]
   [clojure.string :as str]
   [honey.sql :as sql]
   [project.handlers :as handlers]
   [project.routes :as routes]
   [project.server :as server]))



(ns project.core
  (:require
   project.db.json ;; extends JDBC with JSON support
   [project.handlers :as handlers]
   [project.routes :as routes]))



(defn get-user-info [user-id]
  (def user (get-user user-id))
  (def profile-id (:profile-id user))
  (def profile (get-profile profile-id))
  {:user user
   :profile profile})


(defn get-user-info [user-id]
  (let [user (get-user user-id)
        profile-id (:profile-id user)
        profile (get-profile profile-id)]
    {:user user
     :profile profile}))


(let [-rules (read-json "resources/rules.json")]
  (defn get-rule [id]
    (get-in -rules [:system :rules id])))


(def ^:dynamic *locale* nil)

(binding [*locale* :ru]
  (translate :message/hello))


(with-locale :ru
  (translate :message/hello))


(def storage
  (atom {}))

(defn set-item [idx item]
  (swap! storage assoc idx item))

(defn get-item [idx]
  (get @storage idx))


(def *storage
  (atom {}))


(def atom-storage (atom {}))
(def a-storage (atom {}))

(def storage! (atom {}))

(swap! storage! ...)
(reset! storage! ...)


(let [rows! (transient [])]
  (doseq ...
    (conj! rows! item))
  (persistent! rows!))


(defn get-user [user-id]
  (try
    (jdbc/get-by-id *db* :users user-id)
    (catch Throwable e
      e)))



(let [user (get-user 1)]
  (if (throwable? user)
    (do
      (log/error ...)
      (throw user))
    (process user ...)))


(defn get-user [user-id]
  (try
    (jdbc/get-by-id *db* :users user-id)
    (catch Throwable e
      {:type :error :message (ex-message e)})))


(let [user (get-user 1)]
  (if (error-map? user)
    (do
      (log/error ...)
      (throw ...))
    (process user ...)))


(throw (ex-info "Some message" {:some "context"}))



(defn error!

  ([message]
   (throw (ex-info message {})))

  ([message data]
   (throw (ex-info message data)))

  ([message data cause]
   (throw (ex-info message data cause))))


(when-not user
  (e/error! "User not found" {:id user-id}))


(set! *assert* false)

(let [data {:foo 1}]
  (assert (get data :bar) "Bar is missing"))

(set! *assert* false)

(set! *assert* true)

(defn process-user2
  [user]
  {:pre [(some? user)]}
  (assoc user :some "field"))

(process-user2 nil)


(filter #(= (:id %) product-id) products)
(update :body #(json/parse-string % ->kebab-case-keyword))


(filter (fn [product]
          (= (:id product) product-id))
        products)

(update :body json/parse-string ->kebab-case-keyword)



(map inc numbers)

(map str objects)



(map (fn [{:keys [...]}]
       (if this
         (with-something
           (log/info ...)
           (do-that this))
         (let [foo 1
               bar 2]
           (therwise this ...)))) entities)



(defn process-entity [entity]
  ...)

(map process-entity entities)


(for [entity entities]
  (let [{:keys [...]}]
    (return something)))



(->> items
     (map (partial process-item context current-time))
     (filter (partial check-item event-type)))



(def enumerate
  (partial map-indexed vector))

(enumerate [:a :b :c])

([0 :a] [1 :b] [2 :c])


(let [fn-process
      (comp str inc abs)]
  (fn-process -3))


((comp str inc abs) -3)


(map (comp this that more) [...])


(defn make-xform [...]
  (comp (map ...)
        (filter ...)))


(let [xform (make-xform)]
  ...)


(defn connect [host port & {:keys [log-level
                                   log-file]}]
  ...)

(connect "localhost" 5432 :log-level "info" :log-file "log.txt")


(defn connect
  ([host port]
   (connect host port nil))

  ([host port {:keys [log-level log-file]}]
   ...))


(let [options {...}]
  (connect host port options))


(def response
  {:status 200
   :headers {,,,}
   :body {:user {:email "test@test.com"
                 :full-name "Ivan"}}})

(let [{:keys [status body]}
      response

      {:keys [user]}
      body

      {:keys [email full-name]}
      user]

  (println email full-name))


(let [{{{:keys [email full-name]} :user} :body}
      response]
  (println email full-name))


(let [{:keys [:status :body]}
      response]
  ,,,)


(def data
  {:user/name "Ivan"
   :event/name "Party"})


(require 'cheshire.core)

(cheshire.core/generate-string data)

(let [{:user/keys [name]
       :event/keys [name]} data]
  (println name))
;; Party


(let [{user-name :user/name
       event-name :event/name} data]
  (println user-name "@" event-name))


(let [user
      {:user/name "..."
       :user/email "..."
       :user/dob "1985-..."
       :user/active? true}]
  ,,,)

(let [user
      {:name "..."
       :email "..."
       :dob "1985-..."
       :active? true}]
  ,,,)


(let [row
      {:user/name "..."
       :user/email "..."
       :user/dob "1985-..."
       :user/active? true
       :profile/user-id 1
       :profile/created-at "..."
       :profile/avatar "..."}]
  ,,,)


(let [row
      {:user-name "..."
       :user-email "..."
       :user-dob "1985-..."
       :user-active? true
       :profile-user-id 1
       :profile-created-at "..."
       :profile-avatar "..."}]
  ,,,)

(let [row
      {:user {:name "..."
              :email "..."
              :dob "1985-..."
              :active? true}
       :profile {:user-id 1
                 :created-at "..."
                 :avatar "..."}}]
  ,,,)


(require '[clojure.walk :as walk])

(walk/postwalk
 (fn [x]
   (if (map?)
     (update-keys x csk/->SCREAMING_SNAKE_CASE)
     x))
 data)



(let [{:keys [user_id
              user_name]} db-row]
  (println user_id user_name))


(let [{:keys [ResponseError
              ResponseMessage]}
      api-response

      {:keys [Code Category]}
      ResponseError]

  (println Code Category ResponseMessage))





(get-in body [:transactionInfo :userName])

{:transaction-info
 {:user-name {...}}}



{:global-vars {*warn-on-reflection* true
               *assert* true
               *print-meta* false}}


{:user
 {:global-vars {*warn-on-reflection* true
                *assert* true
                *print-meta* false}}}


;; lein uberjar > uberjar.log
;; ! grep -i 'Reflection warning' uberjar.log



(defn upsert-user! [db fields]
  ...)


(defn create-user [db fields]
  ...)

(defn delete-user [db fields]
  ...)

(defn update-user [db fields]
  ...)


(defn get-last-task [db client-id]
  ...)


(defn orders->total [orders]
  ...)


(defn datetime->date [dt]
  ...)


(defn orders+clients->total [orders clients]
  ...)


(defn is-active? [user]
  ...)


(let [active? (is-active? user)]
  ...)

(let [?active (is-active? user)]
  ...)


(defn process-stats [data]
  ...)


(defn process-stats [orders users]
  ...)

(process-stats [{:order-id 1 ...} {:order-id 2 ...}]
               [{:user-id 10 ...} {:user-id 20 ...}])



(defn process-users [id->user]
  ...)

(process-users {1 {:user-id 1 ...},
                2 {:user-id 2 ...}})


(def verb->path->response
  {:get {"/" {:status 200 :body ...}
         "/help" {:status 200 :body ...}}
   :post {"/users" {:status 400 :body ...}}})


(with-http-server verb->path->response
  ...)


(defn error-handler [e]
  ...)

(connect db {:fn-on-error error-handler})


(defn process-events [vec-events int-limit str-notice]
  ...)


(defn process-user [u p]
  ...)

(defn process-user [user profile]
  ...)

(def user {:name "Ivan"})

(let [{:keys [name]} user]
  (println name))

(let [{:keys [full-name]} user]
  (println name))
;; #function[clojure.core/name]


(let [{user-name :name} user]
  (println name))


(defn some-function [a b]
  (with-some-macro {:foo 42}
    (let [x (+ a b)]
      (dotimes [_ 99]
        (println "hello")))))


(calling-a-func-with-args "arg one"
  {:some {:nested "map"}}
  some-variable
  [1 2 3]
  true)


(defn some-long-function [a b c d]
  (let [calc-this
        (some-vast-function a c)

        calc-that
        (some-another-function c d)

        users
        (some-massive-query-to-db calc-that)

        events
        (some-http-request ...)]
                                  ;; <- !!!
    (for [user users]
      ...)))


(defn get-by-id
  ([table id]
   (get-by-id table id :id))

  ([table id pk-name]
   (jdbc/execute ...)))




(defn error!

  ([message]
   (throw (ex-info message {})))

  ([message data]
   (throw (ex-info message data)))

  ([message data cause]
   (throw (ex-info message data cause))))


(defn error!
  ([message]
   (throw (ex-info message {})))
  ([message data]
   (throw (ex-info message data)))
  ([message data cause]
   (throw (ex-info message data cause))))




(defn some-complex-func
  "This function does this and that..."
  [users limit some-arg]
  {:pre [(seq users) (int? limit)]
   :post [(map? %)]}
                     ;; <- !!!
  (let [events
        (get-events ...)]
    ...))



(doseq [item items
        :let [{:keys [id title]} item]
        :when (some? id)]
                           ;; <- !!!
  (process-item ...))



(defn create-user []
  ...)


(defn udpate-user []
  ...)


(defn get-user []
  ...)



(defn some-func [arg1 arg2]
  (let [x 1
        y 2]
              ;; that's
              ;; too much space
    (println ...)))



{:name    "John Smith"
 :active? true
 :email   "john@test.com"}


{:name             "John Smith"
 :id               9
 :active?          true
 :email            "john@test.com"
 :number-of-orders 232}

{:id      9
 :name    "John Smith"
 :email   "john@test.com"
 :active? true

 :another-long-field {...}
 :number-of-orders   232}




(let [id                 (:id item)
      accounts-to-delete (jdbc/query db ["select ..." 42])
      profiles           (rest/get-pending-profiles api "/...")]
  ...)



(let [id
      (:id item)

      accounts-to-delete
      (jdbc/query db ["select ..." 42])

      profiles
      (rest/get-pending-profiles api "/...")]

  (process-all-of-that id
                       accounts-to-delete
                       profiles))




(cond
  (check-this? ...)
  (process-that ...)

  (now-check-that? ...)
  (let [a 1]
    (with-transaction [tx db]
      (jdbc/execute tx ...)))

  (one-more-case? ...)
  (something ...)

  :else
  (default-case ...))

(cond
  (check-this? ...) (process-that ...)
  (now-check-that? ...) (let [a 1]
                          (with-transaction [tx db]
                            (jdbc/execute tx ...)))
  (one-more-case? ...) (something ...)
  :else (default-case ...))


#_{:style/indent 2}

(defmacro then
  {:style/indent 1}
  [value [bind] & body]
  `(let [~bind ~value]
     ~@body))

(defmacro then
  {:style/indent 2}
  ...)

(then 1 [x]
  (inc x))

(-> 1 (then [x] (inc x)))

(-> 1
    (then [x]
      (inc x))
    (then [x]
      (* x x)))


(ns project.codec
  (:import
   java.text.Normalizer
   java.text.Normalizer$Form
   java.security.MessageDigest
   java.util.Base64))


(defn b64-decode ^bytes [^bytes input]
  (.decode (Base64/getDecoder) input))


(defn b64-encode ^bytes [^bytes input]
  (.encode (Base64/getEncoder) input))


(defn normalize-nfc [^String string]
  (Normalizer/normalize string Normalizer$Form/NFC))


(defprotocol IStorage
  (get-this-field [this])
  (get-another-field [this])
  (get-item-by-idx [this idx]))


(deftype Storage
  [map1 map2 map3]

  IStorage

  (get-this-field [this]
    (let [chunk1 (get-in map1 [...])
          chunk2 (get-in map2 [:foo (:id chunk1)])
          chunk3 (get-something-from map3)
          ...]
      {:some "result"}))

  (get-item-by-idx [this idx]
    {:id (get-in map1 some-path)
     :title (get-in map2 another-path)}))


(defn make-storage [map1 map2 map3]
  (new Storage map1 map2 map3))


(let [storage
      (storage/make-storage m1 m2 m3)

      this-field
      (storage/get-this-field storage)

      item
      (storage/get-item-by-idx storage 9)]

  ...)


(defn process-something [^Storage storage]
  (let [item
        (storage/get-item-by-idx storage 9)]
    ...))



(defn -main [& args]
  (let [config
        (read-config ...)]
    (initiate-sytem config)
    (start-system)
    (initiate-cronjobs)
    (initiate-something-else)))


(defn fixture-system [t]
  (initiate-sytem config {:drop [:cronjob]})
  (start-system)
  (t)
  (stop-system))


(defn init-system [config]
  (component/system-map
   :database (db/make-database (:db config))
   :redis (redis/make-redis (:redis config))
   :sendmail (component/using (sendmail/sendmail
                               (:sendmail config))
                              [:database :redis])
   ...))



(def component-map
  {:database db/make-database
   :redis    redis/make-redis
   :sendmail sendmail/sendmail})


(def using-map
  {:sendmail [:database :redis]})



(let [what-is-it?
      (-> 42                  ;; int id
          db/get-user-by-id   ;; a map
          :password           ;; a string field
          .getBytes           ;; byte array
          codec/b64-encode    ;; byte array
          String.)])          ;; string


(defn encode-password ^String [^String password]
  (-> password
      (.getBytes)
      (codec/b64-encode)
      (String.)))


(let [password
      (:password
       (db/get-user-by-id 42))

      password-encoded
      (encode-password password)]

  ...)




(->> entity :info :companies
     (filter #(= some-id (:guid %)))
     first)


(->> (get-in event [:some-field :items])
     (map :amount)
     (filter (every-pred (complement nil?) int?))
     (reduce +))


(->> nodes
     (map #(some-function! entity-id user %))
     (doall)
     (some.ns/process! entity-id)
     (process-data! task-id resource-type end-cursor))



(vec
 (for [item items]
   ...))


(filter #{:active :inactive} statuses)



(reduce
 (fn [acc item]
   (conj acc (:field item)))
 []
 items)


(persistent!
 (reduce
  (fn [acc! item]
    (conj! acc! (:field item)))
  (transient [])
  items))


(loop [i 0
       acc []]
  (if (= i limit)
    acc
    (reduce (inc 0) (conj acc (get-item)))))


(loop [i 0
       acc! (transient [])]
  (if (= i limit)
    (persistent! acc!)
    (reduce (inc 0) (conj! acc! (get-item)))))


(mapv :user-name coll-users)


(vec (for [item items]
       (get-something ...)))


;; never vec/doall this
(for [file (s3/get-files-seq ...)]
  (process-file file))


(s/fdef process-user
  :args (s/cat :user ::user
               :profile ::profile
               :flag? boolean?)
  :ret map?)

(defn process-user [user profile flag?]
  ...)



(defrecord SomeComponent
    [host port state on-error])


(defn make-component [host port & {:keys [on-error]}]
  (map->SomeComponent {:host host
                       :port port
                       :on-error on-error}))


(defrecord SomeComponent
    [;; init
     host port on-error

     ;; runtime
     state

     ;; deps
     cache db])


;; --------------------------
;; -------- Handlers --------
;; --------------------------


;; <><><><><><><><><><><><><>
;; <><><><> Routes <><><><><>
;; <><><><><><><><><><><><><>


(defn some-function [a b c]
  ...)

;; The old version of that function
;; (defn some-function [a b c d]
;;   ...)


(comment

  (def -db
    (jdbc/connect "localhost" 5432))

  (def -row
    (jdbc/execute -db "select 1")))



(deftest test-user-auth-ok
  ...)

(deftest test-user-auth-fails
  ...)


(defn fixture-prepare-db [t]
  (insert-the-data *db*)
  (t)
  (delete-the-data *db*))



(deftest ^:unit test-some-pure-function
  ...)


(deftest ^:integration test-some-db-logic
  ...)


(ns ^:unit project.pure-function-tests
  )

(ns ^:integration project.system-test
  )


(defmacro with-local-http
  [[port verb->path->response] & body]
  `(let [handler#
         (fn [request]
           (get-response-from-mapping))

         server#
         (jetty/run-jetty handler#)]

     (try
       ~@body
       (finally
         (.close server#)))))



(deftest test-some-api
  (with-local-http [8080 {:get {"/v1/users" {...}}}]
    (run-function-that-calls-the-api ...)))



{:dependencies [[amazonica "0.3.156"
                :exclusions [com.amazonaws/aws-java-sdk
                             com.amazonaws/amazon-kinesis-client
                             com.amazonaws/dynamodb-streams-kinesis-adapter]]
               [com.amazonaws/aws-java-sdk-core "1.11.968"]
               [com.amazonaws/aws-java-sdk-s3 "1.11.968"]]}






#_ _
