(fn [& functions]
  (fn [x & y]
    (for [function functions]
      (if (nil? y)
        (function x)
        (apply function x y)))))