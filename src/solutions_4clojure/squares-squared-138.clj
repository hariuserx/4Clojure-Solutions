(fn squares-squared [start end]
  (let [numbers        (loop [ans  []
                              curr start]
                         (let [ans (conj ans curr)
                               sq  (* curr curr)]
                           (if (> sq end)
                             ans
                             (recur ans sq))))
        _              (prn numbers)
        digits         (vec (reduce (fn [ans number]
                                      (concat ans (vec (str number))))
                                    []
                                    numbers))
        _              (prn digits)
        lowest-square  (int (Math/ceil (Math/sqrt (count digits))))
        square-size    (- (* 2 lowest-square) 1)
        row            (* 2 (quot lowest-square 3))
        column         (- lowest-square 1)
        board          (reduce (fn [res _]
                                 (conj res (reduce (fn [res _]
                                                     (conj res \space))
                                                   []
                                                   (range square-size))))
                               []
                               (range square-size))
        directions     {:se [+1 +1]
                        :sw [+1 -1]
                        :nw [-1 -1]
                        :ne [-1 +1]}
        direction-loop {:se :sw
                        :sw :nw
                        :nw :ne
                        :ne :se}
        route          (:route (reduce (fn [ans _]
                                         (prn ans)
                                         (let [current-dir (:current-dir ans)
                                               current-loc (:current-loc ans)
                                               remaining   (:remaining ans)
                                               length      (:length ans)
                                               route       (:route ans)
                                               turns       (:turns ans)
                                               next-loc    [(+ (first current-loc)
                                                               (first (get directions current-dir)))
                                                            (+ (second current-loc)
                                                               (second (get directions current-dir)))]
                                               route       (conj route next-loc)
                                               remaining   (dec remaining)
                                               turns       (if (= 0 remaining)
                                                             (inc turns)
                                                             turns)]
                                           (if (= 0 remaining)
                                             (if (= 2 turns)
                                               {:current-loc next-loc
                                                :current-dir (get direction-loop current-dir)
                                                :length      (inc length)
                                                :remaining   (inc length)
                                                :route       route
                                                :turns       0}
                                               {:current-loc next-loc
                                                :current-dir (get direction-loop current-dir)
                                                :length      length
                                                :remaining   length
                                                :route       route
                                                :turns       turns})
                                             {:current-loc next-loc
                                              :current-dir current-dir
                                              :length      length
                                              :remaining   remaining
                                              :route       route
                                              :turns       turns})))
                                       {:current-loc [row column]
                                        :current-dir :se
                                        :length      1
                                        :remaining   1
                                        :route       [[row column]]
                                        :turns       0}
                                       (rest (range (* lowest-square lowest-square)))))]
    (reduce (fn [ans row]
              (conj ans (clojure.string/join row)))
            []
            (reduce (fn [board i]
                      (let [[r c] (get route i)]
                        (assoc-in board [r c] (or (get digits i)
                                                  \*))))
                    board
                    (range (* lowest-square lowest-square))))))