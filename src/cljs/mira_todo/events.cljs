(ns mira-todo.events
  (:require
   [re-frame.core :as re-frame]
   [mira-todo.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 :edit-list-title
 (fn [db [_ title]]
   (assoc db :list-title title)))

(re-frame/reg-event-db
 :add-empty-list-item
 (fn [db _]
   (update db :todo-list conj {:id (str (gensym)) :name "" :time ""})))

(defn name-updater [todo-item id name]
  (if (= id (:id todo-item))
    (assoc todo-item :name name)
    todo-item))

(re-frame/reg-event-db
 :edit-list-item-name
 (fn [db [_ id name]]
   (assoc db :todo-list (map #(name-updater % id name) (:todo-list db)))))

(defn time-updater [todo-item id time]
  (if (= id (:id todo-item))
    (assoc todo-item :time time)
    todo-item))

(re-frame/reg-event-db
 :edit-list-item-time
 (fn [db [_ id time]]
   (assoc db :todo-list (map #(time-updater % id time) (:todo-list db)))))

(defn status-updater [todo-item id status]
  (if (= id (:id todo-item))
    (assoc todo-item :status status)
    todo-item))

(re-frame/reg-event-db
 :edit-list-item-status
 (fn [db [_ id status]]
   (assoc db :todo-list (map #(status-updater % id status) (:todo-list db)))))
