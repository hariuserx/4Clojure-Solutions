(fn balanced? [num]
  (let [digits     (mapv #(Character/digit % 10)
                         (str num))
        half       (quot (count digits) 2)
        first-half (take half digits)
        last-half  (take half (reverse digits))]
    (= (apply + first-half)
       (apply + last-half))))