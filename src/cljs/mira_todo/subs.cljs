(ns mira-todo.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :list-title
 (fn [db] (:list-title db)))

(re-frame/reg-sub
 :todo-list
 (fn [db] (:todo-list db)))
