(ns mira-todo.views
  (:require
   [re-frame.core :as re-frame]
   [mira-todo.subs :as subs]))

(defn list-title []
  (let [list-title @(re-frame/subscribe [:list-title])]
    [:input#list-title {:placeholder "Todo List Title"
                        :value list-title
                        :on-change (fn [evt] (re-frame/dispatch [:edit-list-title (.. evt -target -value)]))}]))

(defn todo-name-view []
  (let [todo-list @(re-frame/subscribe [:todo-list])]
    [:div
     (into [:ul]
           (map #(vector :li [:input {:placeholder "Task Name"
                                      :value (:name %)
                                      :on-change
                                      (fn [evt] (re-frame/dispatch [:edit-list-item-name
                                                                    (:id %)
                                                                    (.. evt -target -value)]))}]) todo-list))]))

(defn todo-time-view []
  (let [todo-list @(re-frame/subscribe [:todo-list])]
    [:div#todo-time-view
     (into [:ul]
           (map #(vector :li [:input {:placeholder "Task Time (Minutes)"
                                      :value (:time %)
                                      :on-change
                                      (fn [evt] (re-frame/dispatch [:edit-list-item-time
                                                                    (:id %)
                                                                    (.. evt -target -value)]))}]) todo-list))]))

(defn todo-status-view []
  (let [todo-list @(re-frame/subscribe [:todo-list])]
    [:div#todo-status-view
     (into [:ul]
           (map #(vector :li [:input {:type "checkbox"}]) todo-list))]))


(defn todo-list-view []
  [:div#todo-list-view
   [todo-name-view]
   [todo-time-view]
   [todo-status-view]])

(defn add-button []
  [:button {:type "button"
           :on-click
           (fn [] (re-frame/dispatch [:add-empty-list-item]))} "Add Task"])


(defn main-panel []
  [:div
   [list-title]
   [todo-list-view]
   [add-button]])
