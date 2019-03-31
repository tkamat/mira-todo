(ns mira-todo.db)

(def default-db
  {:list-title "TODO List"
   :todo-list [{:id (str (gensym)) :name "Task 1" :time "30" :status :to-do}
               {:id (str (gensym)) :name "Task 2" :time "60" :status :to-do}
               {:id (str (gensym)) :name "Task 3" :time "90" :status :to-do}]})
