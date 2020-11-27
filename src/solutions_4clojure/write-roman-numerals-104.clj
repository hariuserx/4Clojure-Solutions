(fn roman-numeral [number]
  (let [romans       {1    {:symbol "I" :next 5}
                      5    {:symbol "V" :next 10 :prev 1}
                      10   {:symbol "X" :next 50}
                      50   {:symbol "L" :next 100 :prev 10}
                      100  {:symbol "C" :next 500}
                      500  {:symbol "D" :next 1000 :prev 100}
                      1000 {:symbol "M" :next 5000}}
        get-sym      (fn [num]
                       (:symbol (get romans num)))
        get-next-sym (fn [num]
                       (get-sym (:next (get romans num))))
        get-prev-sym (fn [num]
                       (get-sym (:prev (get romans num))))]
    (apply str (:res (reduce (fn helper [{:keys [res number]} div]
                               (if (>= number div)
                                 (let [q (quot number div)
                                       r (rem number div)]
                                   (if (= 4 q)
                                     {:res    (conj res (str (get-sym div)
                                                             (get-next-sym div)))
                                      :number r}
                                     (if (= \9 (first (seq (str number))))
                                       {:res    (conj res (str (get-prev-sym div)
                                                               (get-next-sym div)))
                                        :number (rem number (- (* 2 div) (:prev (get romans div))))}
                                       {:res    (conj res (apply str
                                                                 (repeat q (get-sym div))))
                                        :number r})))
                                 {:res    res
                                  :number number}))
                             {:res    []
                              :number number}
                             [1000 500 100 50 10 5 1])))))