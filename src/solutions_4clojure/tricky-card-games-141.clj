(fn tricky-card-game [trump]
  (fn [cards]
    (let [groups        (group-by :suit cards)
          power-suit    (reduce (fn [res [k v]]
                                  (assoc res
                                    k
                                    (first (sort-by :rank
                                                    #(compare %2 %1)
                                                    v))))
                                {}
                                groups)
          reduced-cards (:res (reduce (fn [{:keys [added-set res]} {:keys [suit]}]
                                        (if (contains? added-set suit)
                                          {:added-set added-set
                                           :res       res}
                                          {:added-set (conj added-set suit)
                                           :res       (conj res (get power-suit suit))}))
                                      {:added-set #{}
                                       :res       []}
                                      cards))]
      (reduce (fn [max-card card]
                (if (= trump (:suit card))
                  card
                  max-card))
              (first reduced-cards)
              (drop 1 reduced-cards)))))