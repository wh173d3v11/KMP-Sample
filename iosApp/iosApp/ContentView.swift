import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    @State private var showBottomSheet = false;
    @State private var product : ProductX? = nil
    
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard) // Compose has its own keyboard handler
            .sheet(isPresented: $showBottomSheet) {
                // Content of the bottom sheet
                BottomSheetView(showBottomSheet: $showBottomSheet, product: $product)
            }
            .onAppear(perform: setupToggleClosure)

    }
    
    private func setupToggleClosure() {
        // Set the toggle closure here
        IOSUtilsKt.toggleiOSProductDetails = { productX in
            // Ensure UI update on the main thread
            DispatchQueue.main.async {
                self.product = productX
                showBottomSheet.toggle()
            }
        }
    }
}

struct BottomSheetView: View {
    @Binding var showBottomSheet: Bool
    @Binding var product : ProductX?
    
    var body: some View {
        VStack(
            alignment: .center,
            spacing: 8
        ) {
            Text(product?.title ?? "No Title")
                .font(.headline)
                .multilineTextAlignment(.center)
                .lineLimit(3)
                .padding(.horizontal)

            AsyncImage(url: URL(string: product?.image ?? "")) { image in
                image
                    .resizable()
                    .scaledToFit()
                    .frame(height: 130)
                    .padding(8)
            } placeholder: {
                Color.gray // Placeholder
                    .frame(height: 130)
                    .padding(8)
            }
            .padding(.horizontal)

            Text(product?.description_ ?? "")
                .font(.body)
                .multilineTextAlignment(.center)
                .lineLimit(4)
                .padding(.horizontal)
                .foregroundColor(.gray)

            Text("$\(product?.price?.doubleValue ?? 0, specifier: "%.2f")")
                .font(.title3)
                .bold()
                .multilineTextAlignment(.center)
                .lineLimit(1)
                .padding(.horizontal)
                .foregroundColor(.gray)

            Button(action: {
                // Action for Buy Now
            }) {
                Text("Buy Now")
                    .fontWeight(.bold)
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(
                        LinearGradient(gradient: Gradient(colors: [.yellow, .red]), startPoint: .leading, endPoint: .trailing)
                    )
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
            .padding(.horizontal, 8)
        }
        .padding()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
        .edgesIgnoringSafeArea(.all)
    }
}



