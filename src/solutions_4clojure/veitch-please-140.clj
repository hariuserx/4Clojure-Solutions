(fn minimize-boolean-expression [expression]
  (let [mappings              {#{'a 'b 'c 'd} [0 0]
                               #{'a 'B 'c 'd} [0 1]
                               #{'A 'B 'c 'd} [0 2]
                               #{'A 'b 'c 'd} [0 3]
                               #{'a 'b 'c 'D} [1 0]
                               #{'a 'B 'c 'D} [1 1]
                               #{'A 'B 'c 'D} [1 2]
                               #{'A 'b 'c 'D} [1 3]
                               #{'a 'b 'C 'D} [2 0]
                               #{'a 'B 'C 'D} [2 1]
                               #{'A 'B 'C 'D} [2 2]
                               #{'A 'b 'C 'D} [2 3]
                               #{'a 'b 'C 'd} [3 0]
                               #{'a 'B 'C 'd} [3 1]
                               #{'A 'B 'C 'd} [3 2]
                               #{'A 'b 'C 'd} [3 3]}
        inverse-mappings      (clojure.set/map-invert mappings)
        boolean-mappings      {'A 'a
                               'a 'A
                               'B 'b
                               'b 'B
                               'C 'c
                               'c 'C
                               'D 'd
                               'd 'D}
        board                 (vec (repeat 4 (vec (repeat 4 0))))
        order-map             [['A 'a] ['B 'b] ['C 'c] ['D 'd]]
        get-expr              (fn get-expr [result index]
                                (if (= 4 index)
                                  result
                                  (let [result (set (for [x result
                                                          y (get order-map index)]
                                                      (conj x y)))]
                                    (get-expr result (inc index)))))
        expression            (get-expr expression (count (first expression)))
        board                 (reduce (fn [result expr]
                                        (let [[i j] (get mappings expr)]
                                          (assoc-in result [i j] 1)))
                                      board
                                      expression)
        ones                  (reduce (fn [result expr]
                                        (let [[i j] (get mappings expr)]
                                          (assoc result [i j] 0)))
                                      {}
                                      expression)
        boxes                 [[4 2] [2 4] [4 1] [1 4] [2 2] [2 1] [1 2] [1 1]]
        get-cells-withing-box (fn [r c [br bc]]
                                (for [i (map #(rem (+ % r) 4) (range br))
                                      j (map #(rem (+ % c) 4) (range bc))]
                                  [i j]))
        all-are-ones?         (fn [vals]
                                (every? (fn [[r c]]
                                          (= 1 (get (get board r) c))) vals))
        result                (reduce (fn [result box]
                                        (let [res (for [r (range 4)
                                                        c (range 4)
                                                        :let [cells (vec (get-cells-withing-box r c box))]
                                                        :when (all-are-ones? cells)]
                                                    [r c box cells])]
                                          (if (empty? res)
                                            result
                                            (vec (concat result (vec res))))))
                                      []
                                      boxes)
        {reduced-result :reduced-result
         ones           :ones} (reduce (fn [{reduced-result :reduced-result
                                             ones           :ones}
                                            res]
                                         (if (every? (fn [[i j]]
                                                       (> (get ones [i j]) 0)) (get res 3))
                                           {:reduced-result reduced-result
                                            :ones           ones}
                                           (let [reduced-result (conj reduced-result res)
                                                 ones           (reduce (fn [ans point]
                                                                          (assoc ans point (inc (get ans point))))
                                                                        ones
                                                                        (get res 3))]
                                             {:reduced-result reduced-result
                                              :ones           ones})))
                                       {:reduced-result []
                                        :ones           ones}
                                       result)
        reduced-result        (reduce (fn [ans res]
                                        (if (every? (fn [point]
                                                      (> (get ones point) 1)) (get res 3))
                                          ans
                                          (conj ans res)))
                                      []
                                      reduced-result)]
    (if (= 16 (count expression))
      1
      (set (map (fn [[_ _ _ points]]
                  (let [ans (reduce (fn [ans point]
                                      (clojure.set/union ans (get inverse-mappings point)))
                                    #{}
                                    points)]
                    (reduce (fn [ans val]
                              (if (contains? ans (get boolean-mappings val))
                                (clojure.set/difference ans #{val (get boolean-mappings val)})
                                ans))
                            ans
                            ans)))
                reduced-result)))))