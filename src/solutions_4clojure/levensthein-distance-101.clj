(fn levenshtein-distance [a b]
  (let [a      (vec (seq a))
        b      (vec (seq b))
        al     (count a)
        bl     (count b)
        holder (conj (vec (range al)) al)]
    (last (reduce (fn [holder row]
                    (reduce (fn [new-holder col]
                              (conj new-holder
                                    (min
                                      (inc (get holder (inc col)))
                                      (inc (get new-holder col))
                                      (if (not= (get a col)
                                                (get b row))
                                        (inc (get holder col))
                                        (get holder col)))))
                            [(inc row)]
                            (range al)))
                  holder
                  (range bl)))))