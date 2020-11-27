(fn largest-mineral [numbers]
  (let [get-binary-lis        (fn get-binary-lis [result number]
                                (if (= 0 number)
                                  result
                                  (let [r      (rem number 2)
                                        q      (quot number 2)
                                        result (conj result r)]
                                    (get-binary-lis result q))))
        slab                  (reduce (fn [res number]
                                        (conj res (get-binary-lis '() number)))
                                      []
                                      numbers)
        columns               (apply max (map count slab))
        rows                  (count numbers)
        slab                  (reduce (fn [result row]
                                        (let [row-size (count row)]
                                          (conj result (vec (concat (repeat (- columns row-size) 0) row)))))
                                      []
                                      slab)
        get-area-for-face     (fn [face-size]
                                (+ face-size (quot (* face-size (- face-size 1)) 2)))
        get-area-if-isosceles (fn [row rr col]
                                (let [row-itr        (range (+ rr 2))
                                      is-strip-ones? (every? #(= 1 %) (map (fn [r]
                                                                             (get (get slab (+ r row)) col)) row-itr))]
                                  (if is-strip-ones?
                                    (let [_             (prn row rr col)
                                          top-right     (reduce (fn [res i]
                                                                  (let [index  (:index res)
                                                                        result (:result res)
                                                                        cs     (rest (range (inc i)))
                                                                        wer    (reduce (fn [res csi]
                                                                                         (conj res [(+ index row) (+ col csi)]))
                                                                                       result
                                                                                       cs)]
                                                                    {:index  (inc index)
                                                                     :result wer}))
                                                                {:index  0
                                                                 :result []}
                                                                (reverse (range (+ rr 2))))
                                          top-left      (reduce (fn [res i]
                                                                  (let [index  (:index res)
                                                                        result (:result res)
                                                                        cs     (rest (range (inc i)))
                                                                        wer    (reduce (fn [res csi]
                                                                                         (conj res [(+ index row) (- col csi)]))
                                                                                       result
                                                                                       cs)]
                                                                    {:index  (inc index)
                                                                     :result wer}))
                                                                {:index  0
                                                                 :result []}
                                                                (reverse (range (+ rr 2))))
                                          bottom-right  (reduce (fn [res i]
                                                                  (let [index  (:index res)
                                                                        result (:result res)
                                                                        cs     (rest (range (inc i)))
                                                                        wer    (reduce (fn [res csi]
                                                                                         (conj res [(+ index row) (+ col csi)]))
                                                                                       result
                                                                                       cs)]
                                                                    {:index  (inc index)
                                                                     :result wer}))
                                                                {:index  0
                                                                 :result []}
                                                                (range (+ rr 2)))
                                          bottom-left   (reduce (fn [res i]
                                                                  (let [index  (:index res)
                                                                        result (:result res)
                                                                        cs     (rest (range (inc i)))
                                                                        wer    (reduce (fn [res csi]
                                                                                         (conj res [(+ index row) (- col csi)]))
                                                                                       result
                                                                                       cs)]
                                                                    {:index  (inc index)
                                                                     :result wer}))
                                                                {:index  0
                                                                 :result []}
                                                                (range (+ rr 2)))
                                          top-right?    (every? #(= 1 %) (map (fn [[r c]]
                                                                                (get (get slab r) c)) (:result top-right)))
                                          top-left?     (every? #(= 1 %) (map (fn [[r c]]
                                                                                (get (get slab r) c)) (:result top-left)))
                                          bottom-right? (every? #(= 1 %) (map (fn [[r c]]
                                                                                (get (get slab r) c)) (:result bottom-right)))
                                          bottom-left?  (every? #(= 1 %) (map (fn [[r c]]
                                                                                (get (get slab r) c)) (:result bottom-left)))
                                          result        []
                                          area          (get-area-for-face (+ rr 2))
                                          result        (if top-right?
                                                          (conj result {:area  area
                                                                        :sides #{#{[row col] [row (+ col (inc rr))]}
                                                                                 #{[row col] [(+ row (inc rr)) col]}}})
                                                          result)
                                          result        (if top-left?
                                                          (conj result {:area  area
                                                                        :sides #{#{[row col] [row (- col (inc rr))]}
                                                                                 #{[row col] [(+ row (inc rr)) col]}}})
                                                          result)
                                          result        (if bottom-right?
                                                          (conj result {:area  area
                                                                        :sides #{#{[(+ row (inc rr)) col] [(+ row (inc rr)) (+ col (inc rr))]}
                                                                                 #{[row col] [(+ row (inc rr)) col]}}})
                                                          result)
                                          result        (if bottom-left?
                                                          (conj result {:area  area
                                                                        :sides #{#{[(+ row (inc rr)) col] [(+ row (inc rr)) (- col (inc rr))]}
                                                                                 #{[row col] [(+ row (inc rr)) col]}}})
                                                          result)]
                                      result)
                                    [])))
        ans                   (reduce (fn [res col]
                                        (reduce (fn [res row]
                                                  (let [remaining-rows (- rows (+ row 1))]
                                                    (reduce (fn [res rr]
                                                              (let [area (get-area-if-isosceles row rr col)]
                                                                (if (not (empty? area))
                                                                  (vec (concat res area))
                                                                  res)))
                                                            res
                                                            (range remaining-rows))))
                                                res
                                                (range rows)))
                                      []
                                      (range columns))
        merged-ans            (reduce (fn [res x]
                                        (conj (vec (reduce (fn [res y]
                                                             (let [sides-x (:sides x)
                                                                   sides-y (:sides y)]
                                                               (if (or (and (contains? sides-y (first sides-x))
                                                                            (let [www       (clojure.set/union (second sides-x) (first (clojure.set/difference sides-y #{(first sides-x)})))
                                                                                  same-row? (apply = (map first www))
                                                                                  same-col? (apply = (map second www))
                                                                                  _         (prn "QQQQ" www same-row? same-col?)]
                                                                              (or same-row? same-col?)))
                                                                       (and (contains? sides-y (second sides-x))
                                                                            (let [www       (clojure.set/union (first sides-x) (first (clojure.set/difference sides-y #{(second sides-x)})))
                                                                                  same-row? (apply = (map first www))
                                                                                  same-col? (apply = (map second www))
                                                                                  _         (prn "wwww" www same-row? same-col?)]
                                                                              (or same-row? same-col?))))
                                                                 (let [new-area (+ (:area x) (quot (:area x) 2))]
                                                                   (conj res {:area  new-area
                                                                              :sides nil}))
                                                                 res)))
                                                           res
                                                           res)) x))
                                      [(first ans)]
                                      (rest ans))
        _                     (prn merged-ans)]
    (if (not (empty? ans))
      (:area (apply max-key (fn [v]
                              (:area v)) merged-ans))
      nil)))