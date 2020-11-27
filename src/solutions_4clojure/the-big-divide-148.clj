(fn the-big-divide [n a b]
  (let [quoter (fn [x]
                 (let [q (quot n x)]
                   (if (= 0 (rem n x))
                     (dec q)
                     q)))
        aq     (quoter a)
        bq     (quoter b)
        abq    (quoter (* a b))
        summer (fn [q x]
                 (quot (reduce *' [q (+ q 1) x]) 2))]
    (- (+ (summer aq a) (summer bq b)) (summer abq (* a b)))))