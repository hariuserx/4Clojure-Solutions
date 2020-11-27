(fn slice-latin-square [board]
  (let [columns               (count (apply max-key count board))
        rows                  (count board)
        get-padded-row        (fn [row padding]
                                (let [row-size      (count row)
                                      left-padding  padding
                                      right-padding (- columns (+ padding row-size))]
                                  (vec (concat (repeat left-padding nil)
                                               row
                                               (repeat right-padding nil)))))
        get-all-combinations  (fn get-all-combinations [row-count
                                                        current-board]
                                (let [current-row (get board row-count)
                                      max-padding (- columns (count current-row))]
                                  (if (= (- rows 1) row-count)
                                    (reduce (fn [result padding]
                                              (let [padded-row (get-padded-row current-row padding)]
                                                (conj result (conj current-board padded-row))))
                                            []
                                            (range (inc max-padding)))
                                    (reduce (fn [result padding]
                                              (let [padded-row (get-padded-row current-row padding)]
                                                (vec (concat result (get-all-combinations (inc row-count)
                                                                                          (conj current-board padded-row))))))
                                            []
                                            (range (inc max-padding))))))
        boards                (get-all-combinations 0 [])
        max-latin-square-size (min columns rows)
        get-square-from-board (fn [row col board square-size]
                                (let [row-reduced-board (vec (take square-size (drop row board)))
                                      x                 (count row-reduced-board)
                                      result            (if (= square-size x)
                                                          (reduce (fn [result r]
                                                                    (conj result (vec (take square-size (drop col r)))))
                                                                  []
                                                                  row-reduced-board)
                                                          nil)
                                      valid?            (and result (reduce (fn [ans r]
                                                                              (and ans (= square-size (count r))))
                                                                            true
                                                                            result))]
                                  (if valid?
                                    result
                                    nil)))
        latin-square?         (fn [square]
                                (let [first-row        (set (first square))
                                      square-transpose (vec (apply map vector square))]
                                  (if (contains? first-row nil)
                                    false
                                    (and (= (count (first square)) (count first-row))
                                         (reduce (fn [result row]
                                                   (and result (= (set row) first-row)))
                                                 true
                                                 square)
                                         (reduce (fn [result row]
                                                   (and result (= (set row) first-row)))
                                                 true
                                                 square-transpose)))))]
    (reduce
      (fn [result val]
        (let [size (count (first val))]
          (if (contains? result size)
            (assoc result size (inc (get result size)))
            (assoc result size 1))))
      {}
      (reduce (fn [result latin-square-size]
                (clojure.set/union result
                                   (reduce (fn [result board]
                                             (clojure.set/union result
                                                                (reduce (fn [result row]
                                                                          (clojure.set/union result
                                                                                             (reduce (fn [result col]
                                                                                                       (let [square (get-square-from-board row col board latin-square-size)]
                                                                                                         (if (and square (latin-square? square))
                                                                                                           (conj result square)
                                                                                                           result)))
                                                                                                     #{}
                                                                                                     (range columns))))
                                                                        #{}
                                                                        (range rows))))
                                           #{}
                                           boards)))
              #{}
              (range 2 (inc max-latin-square-size))))))