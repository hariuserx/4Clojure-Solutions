(fn longest-increasing-subsequence [s]
  (let [[mi mj] ((fn helper [c mi mj i j n]
                   (if (or (> i n) (> j n))
                     [mi mj]
                     (if (> (nth s j) (nth s (dec j)))
                       (if (> (- j i) c)
                         (helper (- j i) i j i (inc j) n)
                         (helper c mi mj i (inc j) n))
                       (helper c mi mj j (inc j) n))))
                 0
                 0
                 -1
                 0
                 1
                 (dec (count s)))]
    (drop mi (take (inc mj) s))))