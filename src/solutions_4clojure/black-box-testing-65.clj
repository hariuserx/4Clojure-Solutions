(fn [val]
  (let [instancex? (fn
                     [c x]
                     (. c (isInstance x)))]
    (cond
      (instancex? clojure.lang.IPersistentMap val) :map
      (instancex? clojure.lang.IPersistentVector val) :vector
      (instancex? clojure.lang.IPersistentSet val) :set
      (instancex? clojure.lang.IPersistentCollection val) :list)))