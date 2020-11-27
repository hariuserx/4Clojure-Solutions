(fn get-dfa-language [{:keys [states
                              alphabet
                              start
                              accepts
                              transitions]}]
  ((fn helper [queue]
     (if (empty? queue)
       []
       (let [{new-queue :new-queue
              result    :result} (reduce (fn [res v]
                                           (let [new-queue     (:new-queue res)
                                                 result        (:result res)
                                                 current-state (:current-state v)
                                                 val           (:val v)
                                                 result        (if (and (contains? accepts current-state) (not (empty? val)))
                                                                 (conj result (clojure.string/join val))
                                                                 result)
                                                 new-queue     (vec (concat new-queue (reduce (fn [res [k v]]
                                                                                                (conj res {:current-state v
                                                                                                           :val           (conj val k)}))
                                                                                              []
                                                                                              (get transitions current-state))))]
                                             {:new-queue new-queue
                                              :result    result}))
                                         {:new-queue []
                                          :result    []}
                                         queue)]
         (lazy-seq (concat result (helper new-queue))))))
   [{:current-state start
     :val           []}]))